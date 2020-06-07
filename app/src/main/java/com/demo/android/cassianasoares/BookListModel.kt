package com.demo.android.cassianasoares

class BookListModel {
    var items: List<BookModel>? = null

    fun getBooks(): List<BookModel>{
        return items!!
    }
}