package com.demo.android.cassianasoares.viewModel

import androidx.lifecycle.ViewModel
import com.demo.android.cassianasoares.room.data.model.Book
import com.demo.android.cassianasoares.room.data.BookRepository

class BookApiViewModel(var repository: BookRepository): ViewModel() {


    fun saveBookFromApi(book: Book){
        repository.saveBook(book)
    }

}