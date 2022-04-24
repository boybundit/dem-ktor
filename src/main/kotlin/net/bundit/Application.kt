package net.bundit

import io.ktor.server.application.*
import net.bundit.plugins.configureContentNegotiation
import net.bundit.plugins.configureStatusPage
import net.bundit.plugins.routing.configureBookRouting
import net.bundit.plugins.routing.configureRouting

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureContentNegotiation()
    configureRouting()
    configureBookRouting()
    configureStatusPage()
}