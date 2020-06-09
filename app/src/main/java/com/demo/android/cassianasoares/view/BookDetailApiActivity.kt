package com.demo.android.cassianasoares.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.demo.android.cassianasoares.R
import kotlinx.android.synthetic.main.activity_book_detail_api.*

class BookDetailApiActivity : AppCompatActivity() {

    var nPages: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail_api)

        initElemnts()

        setSupportActionBar(toolbar_api)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun initElemnts(){
        nPages =  intent.getIntExtra("PAGE", 0)
        Glide.with(this).load(intent.getStringExtra("IMAGE").replace("http", "https")).into(book_image_api)
        txt_book_api.text = intent.getStringExtra("NAME")
        txt_authors_api.text = intent.getStringExtra("AUTHORS").replace("[", "").replace("]", "")
        txt_publisher_api.text = intent.getStringExtra("PUBLISHER")
        txt_n_pages_api.text = nPages.toString()
        txt_categories_api.text = intent.getStringExtra("CATEGORIES").replace("[", "").replace("]", "")
        txt_idioma_api.text = intent.getStringExtra("LANGUAGE")
        txt_description_api.text = intent.getStringExtra("DESCRIPTION")
    }

}
