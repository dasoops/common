package com.dasoops.common.http.server.ktor

import com.dasoops.common.core.util.resources.IgnoreResourcesScan
import com.dasoops.common.core.util.resources.Resources
import io.javalin.Javalin
import io.javalin.config.JavalinConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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
        .mapNotNull {
            it.kotlin.objectInstance?.run {
                return@mapNotNull this as T
            }
            JavalinServer.logger.error("${it.name}不是一个object对象,请将其声明为object以获取plugin-system-http-server支持")
            return@mapNotNull null
        }
}