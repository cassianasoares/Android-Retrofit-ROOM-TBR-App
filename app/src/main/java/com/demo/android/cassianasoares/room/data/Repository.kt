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

    override fun getAllReadingBooks(status: String): LiveData<List<Book>> {
        val allReadingBooks by lazy { bookDao.getAllReadingBooks(status)}
        return allReadingBooks
    }

    override fun getAllReadBooks(status: String): LiveData<List<Book>> {
        val allReadBooks by lazy { bookDao.getAllReadBooks(status)}
        return allReadBooks
    }

    override fun getAllToReadBooks(status: String): LiveData<List<Book>> {
        val allToReadBooks by lazy { bookDao.getAllToReadBooks(status)}
        return allToReadBooks
    }

    override fun getBook(id: String): LiveData<Book> {
        val book by lazy { bookDao.getBook(id)}
        return book
    }

    override fun updateBook(book: Book) {
        AsyncTask.execute { bookDao.updateBook(book) }
    }

    override fun deleteBook(book: Book) {
        AsyncTask.execute { bookDao.deleteBook(book)}
    }
}