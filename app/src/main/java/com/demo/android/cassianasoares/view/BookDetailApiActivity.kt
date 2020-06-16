package com.demo.android.cassianasoares.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.room.data.Book
import com.demo.android.cassianasoares.room.data.Repository
import com.demo.android.cassianasoares.viewModel.BookApiViewModel
import kotlinx.android.synthetic.main.activity_book_detail_api.*

class BookDetailApiActivity : AppCompatActivity() {

    var id: String? = null
    var name: String? = null
    var authors: String? = null
    var url_image: String? = null
    var nPages: Int? = null
    var nReadPages: Int = 0
    var status: String? = null
    var favorite: Boolean = false
    private val TO_READ = "To Read"
    private val READING_NOW = "Reading"
    private val ALREADY_READ = "Read"
    val book_note = 0

    private val bookApiViewModel by lazy { BookApiViewModel(Repository())}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail_api)

        initElemnts()

        setSupportActionBar(toolbar_api)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun initElemnts(){
        id = intent.getStringExtra("ID")
        name = intent.getStringExtra("NAME")
        authors = intent.getStringExtra("AUTHORS")!!.replace("[", "").replace("]", "")
        url_image = intent.getStringExtra("IMAGE")!!.replace("http", "https")
        nPages =  intent.getIntExtra("PAGE", 0)

        Glide.with(this).load(url_image).into(book_image_api)
        txt_book_api.text = name
        txt_authors_api.text = authors
        txt_publisher_api.text = intent.getStringExtra("PUBLISHER")
        txt_n_pages_api.text = nPages.toString()
        txt_categories_api.text = intent.getStringExtra("CATEGORIES")!!.replace("[", "").replace("]", "")
        txt_idioma_api.text = intent.getStringExtra("LANGUAGE")!!.replace("pt", "Portugues").replace("en", "Inglês")
        txt_description_api.text = intent.getStringExtra("DESCRIPTION")
    }

    fun saveBookToRead(view: View) {
        menu_Api.close(true)
        val book: Book = Book(id!!, name!!, authors!!, url_image!!, nPages!!, nReadPages, TO_READ, book_note, favorite)
        bookApiViewModel.saveBookFromApi(book)

        sendBookData(book)
    }

    fun saveBookRead(view: View) {
        menu_Api.close(true)
        val book: Book = Book(id!!, name!!, authors!!, url_image!!, nPages!!, nReadPages, ALREADY_READ, book_note, favorite)
        bookApiViewModel.saveBookFromApi(book)

        sendBookData(book)
    }

    fun saveBookReading(view: View) {
        menu_Api.close(true)
        val book: Book = Book(id!!, name!!, authors!!, url_image!!, nPages!!, nReadPages, READING_NOW, book_note, favorite)
        bookApiViewModel.saveBookFromApi(book)

        sendBookData(book)
    }

    private fun sendBookData(book: Book){
        val intent = Intent(this, BookDetailRoomActivity::class.java)
        intent.putExtra("UUID", book.uuid)
        startActivity(intent)
        onSupportNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
