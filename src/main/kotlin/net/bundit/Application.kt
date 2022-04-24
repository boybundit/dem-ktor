package net.bundit

import freemarker.cache.ClassTemplateLoader
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
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
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
}