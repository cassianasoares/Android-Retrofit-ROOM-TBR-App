package com.demo.android.cassianasoares.api.model

class BookListModel {
    var items: List<BookModel>? = null

    fun getBooks(): List<BookModel>{
        return items!!
    }
}