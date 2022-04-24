package net.bundit.model

import org.jetbrains.exposed.sql.Table
import java.util.concurrent.atomic.AtomicInteger

data class Book(
    val id: Int,
    val title: String,
    val author: String
) {

    companion object {
        private val idCounter = AtomicInteger()
        fun create(title: String, author: String) = Book(idCounter.getAndIncrement(), title, author)
        fun create(inputBook: Book) = Book(idCounter.getAndIncrement(), inputBook.title, inputBook.author)
        fun copy(inputBook: Book) = Book(inputBook.id, inputBook.title, inputBook.author)
    }

}

object Books : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val author = varchar("body", 1024)

    override val primaryKey = PrimaryKey(id)
}