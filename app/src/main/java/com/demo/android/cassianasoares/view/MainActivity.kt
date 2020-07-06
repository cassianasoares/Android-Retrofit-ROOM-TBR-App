package com.demo.android.cassianasoares.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.adapters.BookReadRoomAdapter
import com.demo.android.cassianasoares.adapters.BookReadingRoomAdapter
import com.demo.android.cassianasoares.adapters.BookToReadRoomAdapter
import com.demo.android.cassianasoares.room.data.Repository
import com.demo.android.cassianasoares.room.data.model.ListBookState
import com.demo.android.cassianasoares.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy { MainViewModel(Repository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getQuestionsAndAnswers()
    }

    private fun render(state: ListBookState){
        when(state){
            is ListBookState.EmptyState -> renderEmptyState()
            is ListBookState.DataState -> renderData()
            is ListBookState.LoadingState -> renderLoadingState()
        }
    }

    private fun renderData() {
        layout_listbook_loading.visibility = View.GONE
        layout_listbook_empty.visibility = View.GONE
        layout_listbook_exist.visibility = View.VISIBLE
        initRecyclerViewReading()
        initRecyclerViewRead()
        initRecyclerViewToRead()
    }

    private fun renderLoadingState() {
        layout_listbook_loading.visibility = View.VISIBLE
    }

    private fun renderEmptyState() {
        layout_listbook_loading.visibility = View.GONE
        layout_listbook_exist.visibility = View.GONE
        layout_listbook_empty.visibility = View.VISIBLE
    }


    private fun initRecyclerViewReading(){

        val bookReadingRoomAdpater = BookReadingRoomAdapter(this@MainActivity)

            mainViewModel.getBooksReadingList("Reading").observe(this, Observer
            {                books ->
                books.let { bookReadingRoomAdpater.setBooksReading(it) }
            })

       //booksReading_recyclerview.layoutManager =
           //LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        booksReading_viewpager2.adapter = bookReadingRoomAdpater

        booksReading_viewpager2.clipToPadding = false
        booksReading_viewpager2.clipChildren = false
        booksReading_viewpager2.offscreenPageLimit = 3
        booksReading_viewpager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(15))
        compositePageTransformer.addTransformer(object : ViewPager2.PageTransformer {

            override fun transformPage(page: View, position: Float) {
                val raio : Float = 1 - Math.abs(position)
                page.scaleY = 0.95f + raio * 0.10f
            }

        })
        booksReading_viewpager2.setPageTransformer(compositePageTransformer)
    }

    private fun initRecyclerViewRead() {
        val bookReadRoomAdpater = BookReadRoomAdapter(this@MainActivity)

        mainViewModel.getBooksReadList("Read").observe(this, Observer { books ->
            books.let { bookReadRoomAdpater.setBooksRead(it) }
        })

        booksRead_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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

    private fun getQuestionsAndAnswers(){
        mainViewModel.getCurrentState().observe(this, Observer {
            render(it)
        })
    }

    fun firstBookSearch(view: View) {
        startActivity(Intent(this, SearchActivity::class.java))
    }

}
