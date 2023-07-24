package com.dasoops.common.http.server.ktor

import com.dasoops.common.core.util.resources.IgnoreResourcesScan
import com.dasoops.common.core.util.resources.Resources
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.util.pipeline.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * ktor服务器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/07/24
 * @see [KtorServer]
 */
open class KtorServer(
    private val factory: ApplicationEngineFactory<ApplicationEngine, out ApplicationEngine.Configuration> = Netty,
    private val host: String = "0.0.0.0",
    private val port: Int = 6543,
    private val wait: Boolean = false,
    private val scanBasePath: Collection<String> = listOf("com.dasoops"),
) {
    fun start() {
        embeddedServer(
            factory = factory,
            port = port,
            host = host,
            module = { installModule(scanBasePath) }
        ).start(wait)
    }

    companion object : KtorServer() {
        val logger: Logger = LoggerFactory.getLogger(KtorServer::class.java)
    }
}

@IgnoreResourcesScan
interface ExceptionHandler<T : Throwable> {
    val order: Int
        get() = Int.MAX_VALUE
    val handler: StatusPagesConfig.() -> Unit
}

@IgnoreResourcesScan
interface HttpHandler {
    val handler: Application.() -> Unit
}

@IgnoreResourcesScan
interface HttpInterceptor {
    val order: Int
        get() = Int.MIN_VALUE
    val handler: suspend PipelineContext<Unit, ApplicationCall>.() -> Unit
}

fun Application.installModule(scanBasePath: Collection<String>) {
    install(ContentNegotiation) {
        scan<HttpInterceptor>(scanBasePath).sortedBy { it.order }.forEach { interceptor ->
            this@installModule.intercept(ApplicationCallPipeline.Plugins) {
                interceptor.handler.invoke(this)
                KtorServer.logger.info("register httpInterceptor: ${it.javaClass.name}")
            }
        }

        scan<HttpHandler>(scanBasePath).forEach {
            it.handler.invoke(this@installModule)
            KtorServer.logger.info("register httpHandler: ${it.javaClass.name}")
        }
    }
    install(StatusPages) {
        scan<ExceptionHandler<*>>(scanBasePath)
            .sortedBy { it.order }
            .forEach {
                it.handler.invoke(this)
                KtorServer.logger.info("register httpExceptionHandler: ${it.javaClass.name}")
            }
    }
}

internal inline fun <reified T : Any> scan(scanBasePath: Collection<String>): Collection<T> {
    return Resources.scan(scanBasePath)
        .filter { T::class.java.isAssignableFrom(it) }
        .mapNotNull {
            it.kotlin.objectInstance?.run {
                return@mapNotNull this as T
            }
            KtorServer.logger.error("${it.name}不是一个object对象,请将其声明为object以获取plugin-system-http-server支持")
            return@mapNotNull null
        }
}