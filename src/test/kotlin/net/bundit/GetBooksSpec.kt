package net.bundit

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.testing.*
import net.bundit.model.Book
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
                val client = createClient {
                    install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                        jackson()
                    }
                }
                // When
                val response = client.get("/api/books")
                // Then
                response.status `should be` HttpStatusCode.OK
                val books: Array<Book> = response.body()
                books `should contain` book
            }
        }
    }

})