package net.bundit

import io.ktor.server.application.*
import net.bundit.plugins.configureContentNegotiation
import net.bundit.plugins.configureStatusPage
import net.bundit.plugins.configureTemplateEngine
import net.bundit.plugins.routing.configureRouting
import net.bundit.repository.DatabaseFactory

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()
    configureRouting()
    configureContentNegotiation()
    configureTemplateEngine()
    configureStatusPage()
}