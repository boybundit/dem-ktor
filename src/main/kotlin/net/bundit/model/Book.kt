package net.bundit.model

import java.util.concurrent.atomic.AtomicInteger

data class Book(
    val id: Int,
    val title: String,
    val author: String
){

    companion object {
        private val idCounter = AtomicInteger()
        fun create(title: String, author: String) = Book(idCounter.getAndIncrement(), title, author)
    }

}