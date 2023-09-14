package com.dasoops.common.http.server.ktor

import io.javalin.Javalin

object ExampleHandler : JavalinRegister {
    override val handler: Javalin.() -> Unit
        get() = {
            get("ping") {
                it.result("pong")
            }
        }
}