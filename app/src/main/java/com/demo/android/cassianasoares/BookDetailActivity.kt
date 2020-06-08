package com.demo.android.cassianasoares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    var nPages: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        initElemnts()
    }

    fun initElemnts(){
        nPages =  intent.getIntExtra("PAGE", 0)
        Glide.with(this).load(intent.getStringExtra("IMAGE").replace("http", "https")).into(book_image_detail)
        txt_book_detail.text = intent.getStringExtra("NAME")
        txt_authors_detail.text = intent.getStringExtra("AUTHORS").replace("[", "").replace("]", "")
        txt_publisher_detail.text = "Editora: " + intent.getStringExtra("PUBLISHER")
        txt_n_pages_detail.text = "N° de páginas: " + nPages.toString()
        txt_categories_detail.text = "Genero: " + intent.getStringExtra("CATEGORIES").replace("[", "").replace("]", "")
        txt_language_detail.text = "Idioma: " +intent.getStringExtra("LANGUAGE")
        txt_description_detail.text = intent.getStringExtra("DESCRIPTION")
    }

}
