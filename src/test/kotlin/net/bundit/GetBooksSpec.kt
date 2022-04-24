package net.bundit

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import net.bundit.model.Book
import net.bundit.plugins.configureContentNegotiation
import net.bundit.plugins.routing.configureBookRouting
import net.bundit.plugins.routing.configureRouting
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should contain`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GetBooksSpec : Spek({

    val book = Book(
        0,
        "Unit Testing Principles, Practices, and Patterns",
        "Vladimir Khorikov"
    )

    describe("Get all books") {
        it("should return a list of books") {
            testApplication {
                // Given
                application {
                    configureContentNegotiation()
                    configureRouting()
                    configureBookRouting()
                }
                environment {
                    config = MapApplicationConfig("ktor.environment" to "test")
                }
                val client = createClient {
                    install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                        jackson()
                    }
                }
                // When
                val response = client.get("/api/books")
                // Then
                val books: Array<Book> = response.body()
                response.status `should be` HttpStatusCode.OK
                books `should contain` book
            }
        }
    }

})