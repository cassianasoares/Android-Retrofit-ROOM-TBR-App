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
import com.demo.android.cassianasoares.room.data.Book
import com.demo.android.cassianasoares.view.BookDetailRoomActivity
import kotlinx.android.synthetic.main.item_to_read.view.*

class BookToReadRoomAdapter(private val context: Context): RecyclerView.Adapter<BookToReadRoomAdapter.BookToReadViewHolder>() {

    private var books: List<Book>? = ArrayList()

    class BookToReadViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var book_image = itemView.book_image_ToRead
        var txt_book_name = itemView.txt_book_ToRead
        var txt_book_authors = itemView.txt_authors_ToRead

        fun bind(book: Book){
            Glide.with(itemView).load(book.url_image).into(book_image)
            txt_book_name.text = book.name
            txt_book_authors.text = book.authors
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookToReadViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_to_read, parent, false)
        return BookToReadViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books!!.size
    }

    internal fun setBooksToRead(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BookToReadViewHolder, position: Int) {
        holder.bind(books!![position])

        val book = books!![position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailRoomActivity::class.java)
            intent.putExtra("UUID", book.uuid)
            (context as AppCompatActivity).startActivity(intent)
        }
    }

}