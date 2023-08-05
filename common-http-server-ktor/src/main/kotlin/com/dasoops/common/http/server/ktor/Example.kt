package com.dasoops.common.http.server.ktor

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory

object ExampleHandler : HttpHandler {
    override val handler: Application.() -> Unit = {
        routing {
            get("/ping") {
                call.respond("pong")
            }
        }
    }
}

object ExampleInterceptor : HttpInterceptor {
    override val handler: suspend PipelineContext<Unit, ApplicationCall>.() -> Unit = {
        //
    }
}

object ExampleExceptionHandler : ExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)
    override val handler: StatusPagesConfig.() -> Unit = {
        exception<Throwable> { call, t ->
            logger.error("exampleExceptionHandler catch exception: ", t)
            call.respondText(text = "500: $t", status = HttpStatusCode.InternalServerError)
        }
    }
}