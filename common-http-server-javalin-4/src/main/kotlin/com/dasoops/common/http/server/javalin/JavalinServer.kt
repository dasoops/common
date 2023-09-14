package com.dasoops.common.http.server.ktor

import cn.hutool.core.util.ReflectUtil
import com.dasoops.common.core.util.resources.IgnoreResourcesScan
import com.dasoops.common.core.util.resources.Resources
import io.javalin.Javalin
import io.javalin.core.JavalinConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.Modifier

/**
 * ktor服务器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/07/24
 * @see [JavalinServer]
 */

open class JavalinServer @JvmOverloads constructor(
        private val host: String = "0.0.0.0",
        private val port: Int = 6543,
        private val scanBasePath: Collection<String> = listOf("com.dasoops"),
        private val config: (JavalinConfig) -> Unit = { },
) {
    fun start() = Javalin.create(config).apply {
        installModule(scanBasePath)
    }.start(host, port)


    companion object : JavalinServer() {
        val logger: Logger = LoggerFactory.getLogger(JavalinServer::class.java)
    }
}

@IgnoreResourcesScan
interface JavalinRegister {
    val order: Int
        get() = Int.MAX_VALUE
    val handler: Javalin.() -> Unit
}

fun Javalin.installModule(scanBasePath: Collection<String>) {
    scan<JavalinRegister>(scanBasePath)
            .sortedBy { it.order }
            .forEach {
                it.handler.invoke(this)
                JavalinServer.logger.info("register httpRegister: ${it.javaClass.name}")
            }
}

internal inline fun <reified T : Any> scan(scanBasePath: Collection<String>): Collection<T> {
    return Resources.scan(scanBasePath)
            .filter { T::class.java.isAssignableFrom(it) }
            .map {
                if (it.kotlin.objectInstance != null) {
                    it.kotlin.objectInstance as T
                } else {
                    //包含这个字段
                    val field = ReflectUtil.getField(it, "INSTANCE")
                    if (field != null && Modifier.isStatic(field.modifiers)) {
                        ReflectUtil.getStaticFieldValue(field) as T
                    } else {
                        ReflectUtil.newInstance(it) as T
                    }
                }
            }
}