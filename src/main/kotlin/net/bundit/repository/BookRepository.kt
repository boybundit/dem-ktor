package net.bundit.repository

import net.bundit.model.Book

interface BookRepository {
    suspend fun findAllBooks(): List<Book>
    suspend fun findBook(id: Int): Book?
    suspend fun createBook(title: String, author: String): Book?
    suspend fun updateBook(id: Int, title: String, author: String): Boolean
    suspend fun deleteBook(id: Int): Boolean
}