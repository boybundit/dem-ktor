package net.bundit.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.bundit.plugins.routing.configureBookApiRouting
import net.bundit.plugins.routing.configureBookWebRouting

fun Application.configureRouting() {
    routing {
        val env = environment?.config?.propertyOrNull("ktor.environment")?.getString()
        if (env == "dev") {
            trace { application.log.trace(it.buildText()) }
        }
        get("/hello") {
            call.respondText("Hello World!")
        }
        get("/error") {
            throw java.lang.RuntimeException("Error occurred")
        }
    }
    configureBookApiRouting()
    configureBookWebRouting()
}
