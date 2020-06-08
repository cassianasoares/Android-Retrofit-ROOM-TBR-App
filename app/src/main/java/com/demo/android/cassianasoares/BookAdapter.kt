package com.demo.android.cassianasoares

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search.view.*

class BookAdapter(private val context: Context, private var books: List<BookModel>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val book_image = itemView.livro_image
        val txt_book_name = itemView.txt_livro
        val txt_author_name = itemView.txt_autor
        val txt_idioma = itemView.txt_idioma

        fun bind(book: BookModel){

            if (book.volumeInfo.imageLinks == null) {
                Glide.with(itemView).load(R.drawable.ic_image_defaul).into(book_image)
            }else {
               Log.i("urlImage", book.volumeInfo.imageLinks.thumbnail)
                Glide.with(itemView).load(book.volumeInfo.imageLinks.thumbnail.replace("http", "https")).into(book_image)
           }
            txt_book_name.text = book.volumeInfo.title
            txt_author_name.text = book.volumeInfo.authors.toString().replace("[", "").replace("]", "")
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

        val book_position = books[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra("ID", book_position.id)
            intent.putExtra("NAME", book_position.volumeInfo.title)
            intent.putExtra("AUTHORS", book_position.volumeInfo.authors.toString())
            intent.putExtra("PUBLISHER", book_position.volumeInfo.publisher)
            intent.putExtra("LANGUAGE", book_position.volumeInfo.language)
            intent.putExtra("CATEGORIES", book_position.volumeInfo.categories.toString())
            intent.putExtra("DESCRIPTION", book_position.volumeInfo.description)
            intent.putExtra("IMAGE", book_position.volumeInfo.imageLinks.thumbnail)
            intent.putExtra("PAGE", book_position.volumeInfo.pageCount)
            (context as AppCompatActivity).startActivity(intent)
        }
    }

}