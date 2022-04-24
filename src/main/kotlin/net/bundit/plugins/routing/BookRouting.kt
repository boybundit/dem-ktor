package net.bundit.plugins.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.bundit.model.Book

var books = listOf(
    Book(0, "Unit Testing Principles, Practices, and Patterns", "Vladimir Khorikov"),
    Book(1, "The Five Dysfunctions of a Team: A Leadership Fable", "Patrick M. Lencioni")
)

fun Application.configureBookRouting() {
    routing {
        route("/api") {
            get("/books") {
                call.respond(books)
            }
            get("/books/{id}") {
                try {
                    val id = call.parameters["id"]!!
                    val book = books.find { it.id == id.toInt() }
                    call.respond(book!!)
                } catch (e: Throwable) {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            post("/books") {
                val book = call.receive<Book>()
                val newBook = book.copy(id = books.size)
                books = books + newBook
                call.respond(HttpStatusCode.Created, newBook)
            }
            put("/books/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }
                val book = books.getOrNull(id.toInt())
                if (book == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@put
                }
                val newBook = call.receive<Book>()
                books = books.filter { it.id != newBook.id }
                books  = books + newBook
                call.respond(HttpStatusCode.NoContent)
            }
            delete("/books/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                val book = books.getOrNull(id.toInt())
                if (book == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@delete
                }
                books = books.filter { it.id != id.toInt() }
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}