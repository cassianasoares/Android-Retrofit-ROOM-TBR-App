package com.demo.android.cassianasoares.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.adapters.BookToReadRoomAdpater
import com.demo.android.cassianasoares.room.data.Repository
import com.demo.android.cassianasoares.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy { MainViewModel(Repository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bookToReadRoomAdpater =  BookToReadRoomAdpater(this@MainActivity)

        mainViewModel.getBooksToReadList("To Read").observe(this, Observer { books ->
            books.let { bookToReadRoomAdpater.setBooksToRead(it) }
        })

        toRead_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        toRead_recyclerview.adapter = bookToReadRoomAdpater
    }

    fun getBook(view: View) {
        startActivity( Intent(this, SearchActivity::class.java))
    }

    fun testRoom(view: View) {
        startActivity( Intent(this, BookDetailRoomActivity::class.java))
    }
}
