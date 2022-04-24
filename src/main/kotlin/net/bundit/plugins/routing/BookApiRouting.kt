package net.bundit.plugins.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.bundit.model.Book
import net.bundit.repository.bookRepository

fun Application.configureBookApiRouting() {
    routing {
        route("/api") {
            get("/books") {
                call.respond(bookRepository.findAllBooks())
            }
            get("/books/{id}") {
                try {
                    val id = call.parameters["id"]!!
                    val book = bookRepository.findBook(id.toInt())
                    call.respond(book!!)
                } catch (e: Throwable) {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            post("/books") {
                val inputBook = call.receive<Book>()
                val newBook = bookRepository.createBook(inputBook.title, inputBook.author)
                if (newBook == null) {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@post
                }
                call.respond(HttpStatusCode.Created, newBook)
            }
            put("/books/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }
                val book = bookRepository.findBook(id.toInt())
                if (book == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@put
                }
                val newBook = call.receive<Book>()
                val result = bookRepository.updateBook(
                    id.toInt(),
                    newBook.title,
                    newBook.author
                )
                if (!result) {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@put
                }
                call.respond(HttpStatusCode.NoContent)
            }
            delete("/books/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                val book = bookRepository.findBook(id.toInt())
                if (book == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@delete
                }
                val result = bookRepository.deleteBook(id.toInt())
                if (!result) {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@delete
                }
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}