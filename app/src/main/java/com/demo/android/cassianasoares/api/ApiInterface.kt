package com.demo.android.cassianasoares.api

import com.demo.android.cassianasoares.api.model.BookListModel
import com.demo.android.cassianasoares.api.model.BookModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/books/v1/volumes")
    fun getBooksUsingQuery(@Query("q") query: String): Call<BookListModel>

    @GET("/books/v1/volumes/{id}")
    fun getBook(@Path("id") id:String): Call<BookModel>
}