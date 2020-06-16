package com.demo.android.cassianasoares.room.data.model

sealed class ListBookState {
    object LoadingState : ListBookState()
    object DataState : ListBookState()
    object EmptyState : ListBookState()
}