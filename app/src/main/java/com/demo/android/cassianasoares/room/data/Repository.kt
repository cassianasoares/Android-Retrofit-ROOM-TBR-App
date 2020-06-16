package com.demo.android.cassianasoares.room.data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.demo.android.cassianasoares.BookApplication
import com.demo.android.cassianasoares.room.data.db.BookDao

class Repository: BookRepository {

    private val bookDao: BookDao by lazy {
        BookApplication.database.bookDao() }


    override fun saveBook(book: Book) {
        AsyncTask.execute { bookDao.insert(book) }
    }


    override fun getAllBooksByStatus(status: String): LiveData<List<Book>> {
        val allToReadBooks by lazy { bookDao.getAllBooksByStatus(status)}
        return allToReadBooks
    }

    override fun getBook(id: String): LiveData<Book> {
        val book by lazy { bookDao.getBook(id)}
        return book
    }

    override fun updateStatus(status: String, id: String) {
        AsyncTask.execute { bookDao.updateStatus(status, id)}
    }

    override fun updatePageRead(n_read_pages: Int, id: String) {
        AsyncTask.execute { bookDao.updatePageRead(n_read_pages, id) }
    }

    override fun updateNote(note: Int, id: String) {
        AsyncTask.execute { bookDao.updateNote(note, id)}
    }

    override fun updateFavorite(favorite: Boolean, id: String) {
        AsyncTask.execute { bookDao.updateFavorite(favorite, id)}
    }

    override fun updateBook(book: Book) {
        AsyncTask.execute { bookDao.updateBook(book) }
    }

    override fun deleteBook(book: Book) {
        AsyncTask.execute { bookDao.deleteBook(book)}
    }
}