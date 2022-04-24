package net.bundit.plugins.routing

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        val env = environment?.config?.propertyOrNull("ktor.environment")?.getString()
        if (env == "dev") {
            trace { application.log.trace(it.buildText()) }
        }
        static("/") {
            staticBasePackage = "static"
            resources(".")
            defaultResource("index.html")
        }
        get("/hello") {
            call.respondText("Hello World!")
        }
        get("/error") {
            throw java.lang.RuntimeException("Error occurred")
        }

    }
}
