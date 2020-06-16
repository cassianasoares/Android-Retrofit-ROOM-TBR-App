package com.demo.android.cassianasoares.room.data

import androidx.lifecycle.LiveData

interface BookRepository {

    fun saveBook(book: Book)

    fun getAllBooksByStatus(status: String): LiveData<List<Book>>

    fun getBook(id: String): LiveData<Book>

    fun updateStatus(status: String, id: String)

    fun updatePageRead(n_read_page: Int, id: String)

    fun updateNote(note: Int, id: String)

    fun updateFavorite(favorite: Boolean, id: String)

    fun updateBook(book: Book)

    fun deleteBook(book: Book)

}