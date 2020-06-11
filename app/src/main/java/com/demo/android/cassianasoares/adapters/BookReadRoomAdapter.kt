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
import kotlinx.android.synthetic.main.item_read.view.*

class BookReadRoomAdapter(val context: Context): RecyclerView.Adapter<BookReadRoomAdapter.BookReadViewHolder>() {

    private var books: List<Book>? = ArrayList()

    class BookReadViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var book_image = itemView.book_image_BookRead
        var txt_book_name = itemView.txt_book_BookRead
        var txt_book_authors = itemView.txt_authors_BookRead
        var ratingBar = itemView.rating_bar_BookRead

        fun bind(book: Book){
            Glide.with(itemView).load(book.url_image).into(book_image)
            txt_book_name.text = book.name
            txt_book_authors.text = book.authors
            ratingBar.rating = book.note.toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookReadViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_read, parent, false)
        return BookReadViewHolder(view)
    }


    override fun getItemCount(): Int {
        return books!!.size
    }

    internal fun setBooksRead(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: BookReadViewHolder, position: Int) {
        holder.bind(books!![position])

        val book = books!![position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailRoomActivity::class.java)
            intent.putExtra("UUID", book.uuid)
            intent.putExtra("NAME", book.name)
            intent.putExtra("AUTHORS", book.authors)
            intent.putExtra("IMAGE", book.url_image)
            intent.putExtra("PAGESTOTAL", book.n_pages)
            intent.putExtra("PAGESREAD", book.n_read_pages)
            intent.putExtra("STATUS", book.status)
            intent.putExtra("NOTE", book.note)
            intent.putExtra("FAVORITE", book.favorite)
            (context as AppCompatActivity).startActivity(intent)
        }
    }

}