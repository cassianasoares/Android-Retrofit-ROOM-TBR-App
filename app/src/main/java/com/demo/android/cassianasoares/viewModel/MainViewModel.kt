package com.demo.android.cassianasoares.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.android.cassianasoares.room.data.Book
import com.demo.android.cassianasoares.room.data.BookRepository

class MainViewModel(var reposiitory: BookRepository): ViewModel() {



    fun getBooksToReadList(status: String): LiveData<List<Book>> {
        val allBooks = reposiitory.getAllToReadBooks(status)
        return allBooks
    }

}