package com.demo.android.cassianasoares.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.android.cassianasoares.room.data.Book
import com.demo.android.cassianasoares.room.data.BookRepository

class EditBookViewModel(var repository: BookRepository): ViewModel() {

    fun updateBook(book: Book){
        repository.updateBook(book)
    }

    fun getBook(id: String): LiveData<Book> {
        val bookget = repository.getBook(id)
        return bookget
    }

}