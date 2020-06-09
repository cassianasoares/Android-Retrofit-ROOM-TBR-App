package com.demo.android.cassianasoares.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.demo.android.cassianasoares.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getBook(view: View) {
        startActivity( Intent(this, SearchActivity::class.java))
    }

    fun testRoom(view: View) {
        startActivity( Intent(this, BookDetailRoomActivity::class.java))
    }
}
