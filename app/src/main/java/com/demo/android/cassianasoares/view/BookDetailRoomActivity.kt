package com.demo.android.cassianasoares.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.android.cassianasoares.R
import kotlinx.android.synthetic.main.activity_book_detail_room.*

class BookDetailRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail_room)

        setSupportActionBar(toolbar_room)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
