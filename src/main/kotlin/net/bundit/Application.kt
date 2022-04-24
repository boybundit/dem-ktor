package net.bundit

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.bundit.plugins.configureContentNegotiation
import net.bundit.plugins.configureStatusPage
import net.bundit.plugins.routing.configureBookRouting
import net.bundit.plugins.routing.configureRouting

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureContentNegotiation()
        configureRouting()
        configureBookRouting()
        configureStatusPage()
    }.start(wait = true)
}
