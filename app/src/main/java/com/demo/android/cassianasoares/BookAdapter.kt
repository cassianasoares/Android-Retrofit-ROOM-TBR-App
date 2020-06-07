package com.demo.android.cassianasoares

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_search.view.*

class BookAdapter(private val context: Context, private var books: List<BookModel>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val book_image = itemView.livro_image
        val txt_book_name = itemView.txt_livro
        val txt_author_name = itemView.txt_autor
        val txt_idioma = itemView.txt_idioma

        fun bind(book: BookModel){/*
            if (book.volumeInfo.imageLinks == null) {
                Picasso.get().load(R.drawable.ic_image_defaul).into(book_image)
            }else {*/
             //  Log.i("urlImage", book.volumeInfo.imageLinks.thumbnail)
                Picasso.get().load("http://books.google.com/books/content?id=yTCODwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api").into(book_image)

           //}

            txt_book_name.text = book.volumeInfo.title
            txt_author_name.text = book.volumeInfo.authors.toString()
            txt_idioma.text = book.volumeInfo.language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

}