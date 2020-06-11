package com.demo.android.cassianasoares.room.data

import androidx.lifecycle.LiveData

interface BookRepository {

    fun saveBook(book: Book)

    fun getAllBooksByStatus(status: String): LiveData<List<Book>>

    fun getBook(id: String): LiveData<Book>

    fun updateBook(book: Book)

    fun deleteBook(book: Book)

}