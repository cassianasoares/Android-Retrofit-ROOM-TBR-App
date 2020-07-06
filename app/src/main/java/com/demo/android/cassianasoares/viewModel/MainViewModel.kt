package com.demo.android.cassianasoares.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.demo.android.cassianasoares.room.data.model.Book
import com.demo.android.cassianasoares.room.data.BookRepository
import com.demo.android.cassianasoares.room.data.model.ListBookState

class MainViewModel(var reposiitory: BookRepository): ViewModel() {

    val allBooksSaved = reposiitory.getAllBooksSaved()
    private val currentState = MediatorLiveData<ListBookState>()

    init {
        currentState.postValue(ListBookState.LoadingState)
        addStateSources()
    }

    fun getCurrentState(): LiveData<ListBookState> = currentState

    fun getAllBooksByStatusList(status: String): LiveData<List<Book>> {
        val allBooksReading = reposiitory.getAllBooksByStatus(status)
        return allBooksReading
    }


    fun addStateSources(){

        currentState.addSource(allBooksSaved){
                allQuestionAndAnswers ->
            if (allQuestionAndAnswers.isEmpty()){
                currentState.postValue(ListBookState.EmptyState)
            }else{
                currentState.postValue(ListBookState.DataState)
            }
        }

    }

}