package com.demo.android.cassianasoares.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.api.ApiClient
import com.demo.android.cassianasoares.api.ApiInterface
import com.demo.android.cassianasoares.api.model.BookModel
import com.demo.android.cassianasoares.room.data.Book
import kotlinx.android.synthetic.main.activity_book_detail_room.*
import kotlinx.android.synthetic.main.item_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookDetailRoomActivity : AppCompatActivity() {

    var id: String? = null
    var name: String? = null
    var authors: String? = null
    var url_image: String? = null
    var nPages: Int? = null
    var nReadPages: Int? = null
    var status: String? = null
    var favorite: Boolean = false
    var book_note: Int? = null

    var apiInterface: ApiInterface? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail_room)

        setSupportActionBar(toolbar_room)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        apiInterface = ApiClient().getClient().create(ApiInterface::class.java)
        initElements()

    }

    fun initElements(){
        id = intent.getStringExtra("UUID")
        name = intent.getStringExtra("NAME")
        authors = intent.getStringExtra("AUTHORS")
        url_image = intent.getStringExtra("IMAGE")
        nPages = intent.getIntExtra("PAGESTOTAL", 0)
        nReadPages = intent.getIntExtra("PAGESREAD", 0)
        status = intent.getStringExtra("STATUS")
        book_note = intent.getIntExtra("NOTE", 0)
        favorite = intent.getBooleanExtra("FAVORITE", false)

        Glide.with(this).load(url_image).into(book_image_room)
        txt_book_room.text = name
        txt_authors_room.text  = authors
        txt_n_pages_room.text = nPages!!.toString()
        rating_bar_indicator.rating = book_note!!.toFloat()

        getStatus()
        getOneBook()
    }

    fun getOneBook() {
        val callBook = apiInterface!!.getBook(id!!)
        callBook.enqueue(object : Callback<BookModel> {
            override fun onResponse(call: Call<BookModel>, response: Response<BookModel>) {
                val book =  response.body()

                if (book!!.volumeInfo.authors == null){
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

                txt_publisher_room.text = book.volumeInfo.publisher
                txt_categories_room.text = book.volumeInfo.categories.toString().replace("[", "").replace("]", "")
                txt_idioma_room.text = book.volumeInfo.language
                txt_description_room.text = book.volumeInfo.description
            }

            override fun onFailure(call: Call<BookModel>, t: Throwable) {
                Log.i("ROOM", "onFailure: " + t.localizedMessage)
            }
        })
    }


    fun getStatus(){
        if (status == "To Read"){
            btn_start_read.visibility = View.VISIBLE
        }else if(status == "Reading"){
            rating_bar_indicator.visibility = View.VISIBLE
            rating_bar_indicator.rating = 3.toFloat()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
