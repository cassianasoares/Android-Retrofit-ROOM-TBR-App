package com.demo.android.cassianasoares.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.android.cassianasoares.view.BookDetailApiActivity
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.api.model.BookModel
import kotlinx.android.synthetic.main.item_search.view.*

class BookApiAdapter(private val context: Context, private var books: ArrayList<BookModel>): RecyclerView.Adapter<BookApiAdapter.BookViewHolder>() {

    var link: String = "https://books.google.com/books/content?id=ksdeAAAAcAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE718AUGEFaRcEfXmQrMoJkYudWE0aB8H58v4-E31cy64tPLAsKo-mtugPqdnZLNA2X3R9m9BbbHP_cGVtljZynhbR8A7ebcUjt9cggp3Lvo0HbaAgwDTAPKuHr-5xF_aYUP-pAqT&source=gbs_api"

    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val book_image = itemView.livro_image
        val txt_book_name = itemView.txt_livro
        val txt_author_name = itemView.txt_autor
        val txt_idioma = itemView.txt_idioma

        fun bind(book: BookModel){
            if(book.volumeInfo.imageLinks == null){
                Glide.with(itemView).load("https://books.google.com/books/content?id=ksdeAAAAcAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE718AUGEFaRcEfXmQrMoJkYudWE0aB8H58v4-E31cy64tPLAsKo-mtugPqdnZLNA2X3R9m9BbbHP_cGVtljZynhbR8A7ebcUjt9cggp3Lvo0HbaAgwDTAPKuHr-5xF_aYUP-pAqT&source=gbs_api").into(book_image)
            }else{
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
        return BookViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])

        val book_position = books[position]

        holder.itemView.setOnClickListener {
            var image: String = ""
            if ( book_position.volumeInfo.imageLinks == null){
                image = "https://books.google.com/books/content?id=ksdeAAAAcAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE718AUGEFaRcEfXmQrMoJkYudWE0aB8H58v4-E31cy64tPLAsKo-mtugPqdnZLNA2X3R9m9BbbHP_cGVtljZynhbR8A7ebcUjt9cggp3Lvo0HbaAgwDTAPKuHr-5xF_aYUP-pAqT&source=gbs_api"
            }else{
                image =  book_position.volumeInfo.imageLinks.thumbnail
            }
            val intent = Intent(context, BookDetailApiActivity::class.java)
            intent.putExtra("ID", book_position.id)
            intent.putExtra("NAME", book_position.volumeInfo.title)
            intent.putExtra("AUTHORS", book_position.volumeInfo.authors.toString())
            intent.putExtra("PUBLISHER", book_position.volumeInfo.publisher)
            intent.putExtra("LANGUAGE", book_position.volumeInfo.language)
            intent.putExtra("CATEGORIES", book_position.volumeInfo.categories.toString())
            intent.putExtra("DESCRIPTION", book_position.volumeInfo.description)
            intent.putExtra("IMAGE", image)
            intent.putExtra("PAGE", book_position.volumeInfo.pageCount)
            (context as AppCompatActivity).startActivity(intent)
        }
    }

}