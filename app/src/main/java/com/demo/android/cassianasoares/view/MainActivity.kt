package com.demo.android.cassianasoares.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.adapters.BookReadRoomAdapter
import com.demo.android.cassianasoares.adapters.BookReadingRoomAdapter
import com.demo.android.cassianasoares.adapters.BookToReadRoomAdapter
import com.demo.android.cassianasoares.room.data.Repository
import com.demo.android.cassianasoares.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy { MainViewModel(Repository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerViewReading()
        initRecyclerViewRead()
        initRecyclerViewToRead()
    }

   private fun initRecyclerViewReading(){


        val bookReadingRoomAdpater = BookReadingRoomAdapter(this@MainActivity)


            mainViewModel.getBooksReadingList("Reading").observe(this, Observer
            {                books ->
                books.let { bookReadingRoomAdpater.setBooksReading(it) }
            })

       booksReading_recyclerview.layoutManager =
           LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            booksReading_recyclerview.adapter = bookReadingRoomAdpater

    }


    private fun initRecyclerViewRead() {
        val bookReadRoomAdpater = BookReadRoomAdapter(this@MainActivity)

        mainViewModel.getBooksReadList("Read").observe(this, Observer { books ->
            books.let { bookReadRoomAdpater.setBooksRead(it) }
        })

        booksRead_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        booksRead_recyclerview.adapter = bookReadRoomAdpater
    }

    private fun initRecyclerViewToRead() {
        val bookToReadRoomAdpater = BookToReadRoomAdapter(this@MainActivity)

        mainViewModel.getBooksToReadList("To Read").observe(this, Observer { books ->
            books.let { bookToReadRoomAdpater.setBooksToRead(it) }
        })

        toRead_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        toRead_recyclerview.adapter = bookToReadRoomAdpater
    }

    fun getBook(view: View) {
        startActivity( Intent(this, SearchActivity::class.java))
    }

}
