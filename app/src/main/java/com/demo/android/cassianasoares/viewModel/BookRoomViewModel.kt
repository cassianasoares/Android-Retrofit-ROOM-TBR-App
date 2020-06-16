package com.demo.android.cassianasoares.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.android.cassianasoares.room.data.model.Book
import com.demo.android.cassianasoares.room.data.BookRepository

class BookRoomViewModel(var repository: BookRepository): ViewModel() {

    fun deleteBook(book: Book){
        repository.deleteBook(book)
    }

    fun getBook(id: String): LiveData<Book>{
        val bookget = repository.getBook(id)
        return bookget
    }

    fun updateStatus(status: String, id: String){
        repository.updateStatus(status, id)
    }

    fun updateReading(n_read_pages: Int, id: String){
        repository.updatePageRead(n_read_pages, id)
    }

    fun updateNote(note: Int, id: String){
        repository.updateNote(note, id)
    }

    fun updateFavorite(isFavorite: Boolean, id: String){
        repository.updateFavorite(isFavorite, id)
    }

}