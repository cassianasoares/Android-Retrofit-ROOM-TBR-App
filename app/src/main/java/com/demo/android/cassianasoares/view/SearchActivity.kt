package com.demo.android.cassianasoares.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.api.ApiClient
import com.demo.android.cassianasoares.api.ApiInterface
import com.demo.android.cassianasoares.adapters.BookApiAdapter
import com.demo.android.cassianasoares.api.model.BookListModel
import com.demo.android.cassianasoares.api.model.BookModel
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private val TAG = "SearchActivity"
    var apiInterface: ApiInterface? = null
    var booksApiAdapter: BookApiAdapter? = null
    var link = "https://books.google.com/books/content?id=ksdeAAAAcAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE718AUGEFaRcEfXmQrMoJkYudWE0aB8H58v4-E31cy64tPLAsKo-mtugPqdnZLNA2X3R9m9BbbHP_cGVtljZynhbR8A7ebcUjt9cggp3Lvo0HbaAgwDTAPKuHr-5xF_aYUP-pAqT&source=gbs_api"

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
                        val booksListReview: ArrayList<BookModel>? = ArrayList()

                        for (book in booksList){
                            if (book.volumeInfo.authors == null){
                                book.volumeInfo.authors = listOf("Desconhecido")
                            }
                            if (book.volumeInfo.categories == null){
                                book.volumeInfo.categories = listOf("Nenhuma")
                            }
                            if(book.volumeInfo.description == null){
                                book.volumeInfo.description = "Nenhuma"
                            }
                            if (book.volumeInfo.publisher == null){
                                book.volumeInfo.publisher = "Deconhecida"
                            }
                            if (book.volumeInfo.language == null){
                                book.volumeInfo.language = "Desconhecida"
                            }


                            booksListReview!!.add(book)
                            Log.i("TesteArray", booksListReview.toString())
                        }

                        booksApiAdapter =
                            BookApiAdapter(
                                this@SearchActivity,
                                booksListReview!!
                            )
                        search_recyclerview.adapter = booksApiAdapter
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
