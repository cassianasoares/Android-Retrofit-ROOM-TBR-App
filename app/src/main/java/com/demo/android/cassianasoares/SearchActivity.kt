package com.demo.android.cassianasoares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private val TAG = "SearchActivity"
    var apiInterface: ApiInterface? = null
    var booksAdapter: BookAdapter? = null
    var bookList: List<BookModel>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        apiInterface = ApiClient().getClient().create(ApiInterface::class.java)
        search_recyclerview.layoutManager = LinearLayoutManager(this)
    }


    fun searchBook(view: View){
        val bookQuery = edt_query.text.toString()
        Log.i("isQueryGet", bookQuery)
        if (bookQuery != null){
        val callBooks = apiInterface!!.getBooksUsingQuery(bookQuery)
        callBooks.enqueue(object : Callback<BookListModel>{

                override fun onResponse(call: Call<BookListModel>, response: Response<BookListModel>) {
                    if (response.isSuccessful){
                        val booksList = response.body()!!.getBooks()

                        booksAdapter = BookAdapter(applicationContext, booksList)
                        search_recyclerview.adapter = booksAdapter
                    }else{
                        Toast.makeText(applicationContext, "Livro não encontrada", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BookListModel>, t: Throwable) {
                    Log.i(TAG, "onFailure: " + t.localizedMessage)
                }

            })

        }else{
            Toast.makeText(this, "Digite o nome do livro", Toast.LENGTH_SHORT).show()
            edt_query.requestFocus()
        }
    }


    /*fun (view: View) {
        val book = edt_query.text.toString()
        if (book != null){
            val callBook = apiInterface!!.getBook(book)
            callBook.enqueue(object : Callback<BookModel> {
                override fun onResponse(call: Call<BookModel>, response: Response<BookModel>) {

                    //Log.i(TAG, "onResponse: " + response.body())
                }

                override fun onFailure(call: Call<BookModel>, t: Throwable) {
                    Log.i(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        }else{
            Toast.makeText(this, "Digite o nome do livro", Toast.LENGTH_SHORT).show()
            edt_query.requestFocus()
        }
    }*/
}
