package com.demo.android.cassianasoares.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.android.cassianasoares.room.data.Book
import com.demo.android.cassianasoares.room.data.BookRepository

class MainViewModel(var reposiitory: BookRepository): ViewModel() {


    fun getBooksReadingList(status: String): LiveData<List<Book>> {
        val allBooks = reposiitory.getAllBooksByStatus(status)
        return allBooks
    }

    fun getBooksReadList(status: String): LiveData<List<Book>> {
        val allBooks = reposiitory.getAllBooksByStatus(status)
        return allBooks
    }

    fun getBooksToReadList(status: String): LiveData<List<Book>> {
        val allBooks = reposiitory.getAllBooksByStatus(status)
        return allBooks
    }

}