package com.demo.android.cassianasoares.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.room.data.model.Book
import com.demo.android.cassianasoares.view.BookDetailRoomActivity
import kotlinx.android.synthetic.main.item_reading.view.*

class BookReadingRoomAdapter(val context: Context): RecyclerView.Adapter<BookReadingRoomAdapter.BookReadingViewHolder>() {

    private var books: List<Book>? = ArrayList()

    class BookReadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var book_image = itemView.book_image_Reading
        var txt_book_name = itemView.txt_book_Reading
        var txt_book_authors = itemView.txt_authors_Reading
        var progressBar = itemView.progressbar_Reading
        var txt_percent = itemView.txt_percent_Reading
        var image_favorite = itemView.image_isfavorite_Reading

        fun bind(book: Book){
            Glide.with(itemView).load(book.url_image).into(book_image)
            txt_book_name.text = book.name
            txt_book_authors.text = book.authors
            val read_percent = (book.n_read_pages * 100) / book.n_pages
            progressBar.progress = read_percent
            txt_percent.text = read_percent.toString() + "%"
            if (book.favorite == true){
                image_favorite.visibility = View.VISIBLE
            }else{
                image_favorite.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookReadingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reading, parent, false)
        return BookReadingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books!!.size
    }

    internal fun setBooksReading(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BookReadingViewHolder, position: Int) {
        holder.bind(books!![position])

        val book = books!![position]

        holder.itemView.setOnClickListener {

            val intent = Intent(context, BookDetailRoomActivity::class.java)
            intent.putExtra("UUID", book.uuid)
            (context as AppCompatActivity).startActivity(intent)
        }
    }

}