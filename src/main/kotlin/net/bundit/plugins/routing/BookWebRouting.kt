package net.bundit.plugins.routing

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureBookWebRouting() {
    routing {
        static("/") {
            staticBasePackage = "static"
            resources(".")
            defaultResource("index.html")
        }
        route("/books") {
            get {
                call.respond(FreeMarkerContent("books.ftl", mapOf("books" to books)))
            }
            get("/{id}") {

            }
        }
    }
}