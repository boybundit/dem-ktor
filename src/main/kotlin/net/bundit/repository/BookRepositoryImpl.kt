package net.bundit.repository

import kotlinx.coroutines.runBlocking
import net.bundit.model.Book
import net.bundit.model.Books
import net.bundit.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*

class BookRepositoryImpl : BookRepository {

    private fun resultRowToBook(row: ResultRow) = Book(
        id = row[Books.id],
        title = row[Books.title],
        author = row[Books.author],
    )

    override suspend fun findAllBooks(): List<Book> = dbQuery {
        Books.selectAll().map(::resultRowToBook)
    }

    override suspend fun findBook(id: Int): Book? = dbQuery {
        Books
            .select { Books.id eq id }
            .map(::resultRowToBook)
            .singleOrNull()
    }

    override suspend fun createBook(title: String, author: String): Book? = dbQuery {
        val insertStatement = Books.insert {
            it[Books.title] = title
            it[Books.author] = author
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToBook)
    }

    override suspend fun updateBook(id: Int, title: String, author: String): Boolean = dbQuery {
        Books.update({ Books.id eq id }) {
            it[Books.title] = title
            it[Books.author] = author
        } > 0
    }

    override suspend fun deleteBook(id: Int): Boolean = dbQuery {
        Books.deleteWhere { Books.id eq id } > 0
    }
}

val bookRepository: BookRepository = BookRepositoryImpl().apply {
    runBlocking {
        if (findAllBooks().isEmpty()) {
            createBook(
                "Unit Testing Principles, Practices, and Patterns",
                "Vladimir Khorikov"
            )
            createBook(
                "The Five Dysfunctions of a Team: A Leadership Fable",
                "Patrick M. Lencioni"
            )

        }
    }
}
