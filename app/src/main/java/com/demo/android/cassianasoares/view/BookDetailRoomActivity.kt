package com.demo.android.cassianasoares.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.api.ApiClient
import com.demo.android.cassianasoares.api.ApiInterface
import com.demo.android.cassianasoares.api.model.BookModel
import com.demo.android.cassianasoares.room.data.model.Book
import com.demo.android.cassianasoares.room.data.Repository
import com.demo.android.cassianasoares.viewModel.BookRoomViewModel
import kotlinx.android.synthetic.main.activity_book_detail_room.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookDetailRoomActivity : AppCompatActivity(){

    var id: String? = null
    var name: String? = null
    var authors: String? = null
    var url_image: String? = null
    var nPages: Int? = null
    var nReadPages: Int? = null
    var status: String? = null
    var favorite: Boolean = false
    var book_note: Int? = null
    var bookSelect: Book? = null

    var apiInterface: ApiInterface? = null
    private val bookRoomViewModel by lazy { BookRoomViewModel(Repository()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail_room)

        setSupportActionBar(toolbar_room)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        apiInterface = ApiClient().getClient().create(ApiInterface::class.java)
        getData()

    }

    fun getData(){
        id = intent.getStringExtra("UUID")

        bookRoomViewModel.getBook(id!!).observe(this, Observer
        {                books ->
            books.let { setBook(it) }
        })

    }

    private fun setBook(book: Book?) {

        if(book != null){
            bookSelect = book
            name = book.name
            authors = book.authors
            url_image = book.url_image
            nPages = book.n_pages
            nReadPages = book.n_read_pages
            status = book.status
            book_note = book.note
            favorite = book.favorite

            Glide.with(this).load(url_image).into(book_image_room)
            txt_book_room.text = name
            txt_authors_room.text  = authors
            txt_n_pages_room.text = nPages!!.toString()
            rating_bar_Room.rating = book_note!!.toFloat()

            rating_bar_Room.onRatingBarChangeListener = object : RatingBar.OnRatingBarChangeListener {

                override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean
                ) {
                    bookRoomViewModel.updateNote(rating.toInt(), id!!)
                }
            }

            toggle_favorite.isChecked = bookSelect!!.favorite != false

            getStatus()
            getOneBook()
        }

    }

    fun getOneBook() {
        val callBook = apiInterface!!.getBook(id!!)
        callBook.enqueue(object : Callback<BookModel> {
            override fun onResponse(call: Call<BookModel>, response: Response<BookModel>) {
                val book =  response.body()

                if (book!!.volumeInfo.authors == null){
                    book.volumeInfo.authors = listOf("Desconhecido")
                }
                if (book.volumeInfo.categories == null){
                    book.volumeInfo.categories = listOf("Nenhuma")
                }
                if(book.volumeInfo.description == null){
                    book.volumeInfo.description = "Nenhuma"
                }
                if (book.volumeInfo.publisher == null){
                    book.volumeInfo.publisher = "Deconhecida"
                }
                if (book.volumeInfo.language == null){
                    book.volumeInfo.language = "Desconhecida"
                }

                txt_publisher_room.text = book.volumeInfo.publisher
                txt_categories_room.text = book.volumeInfo.categories.toString().replace("[", "").replace("]", "")
                txt_idioma_room.text = book.volumeInfo.language.replace("pt", "Portugues").replace("en", "Inglês")
                txt_description_room.text = book.volumeInfo.description
            }

            override fun onFailure(call: Call<BookModel>, t: Throwable) {
                Log.i("ROOM", "onFailure: " + t.localizedMessage)
            }
        })
    }


    fun getStatus(){
        if (status == "To Read"){
            btn_start_read.visibility = View.VISIBLE
            rating_bar_Room.visibility = View.GONE
        }else if(status == "Reading"){
            painel_progress_reading.visibility = View.VISIBLE
            txt_pages_reading_now.text = nReadPages.toString() + " páginas"
            val read_percent = (nReadPages!! * 100) / nPages!!
            Log.i("read", read_percent.toString())
            progressbar_Reding_Room.progress = read_percent
            txt_percent_read_Room.text = read_percent.toString() + "%"
            btn_finish_read.visibility = View.VISIBLE
            btn_start_read.visibility = View.GONE
            rating_bar_Room.visibility = View.GONE
        }else if(status == "Read"){
            rating_bar_Room.visibility = View.VISIBLE
            btn_start_read.visibility = View.GONE
            btn_finish_read.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun editBook(view: View) {
        menu_Room.close(true)
        if (bookSelect != null){
            val intent = Intent(this, EditBook::class.java)
            intent.putExtra("BOOKID", id)
            startActivity(intent)
        }
    }

    fun deleteBook(view: View) {
        menu_Room.close(true)

        val builder = AlertDialog.Builder(this@BookDetailRoomActivity)

        builder.setTitle(R.string.title_aviso)
        builder.setCancelable(true)
        builder.setMessage("Tem certeza que deseja excluir esse livro?")

            .setPositiveButton("Deletar") { _, _ ->

                onSupportNavigateUp()
                bookRoomViewModel.deleteBook(bookSelect!!)
                Toast.makeText(this, "Book delete!", Toast.LENGTH_LONG).show()

            }
            // negative button text and action
            .setNegativeButton(getString(R.string.cancelar_alert)) { dialog, _ ->
                dialog.cancel()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    fun updateFavorite(view: View) {
        if(bookSelect!!.favorite == false){
            toggle_favorite.isChecked = true
            bookRoomViewModel.updateFavorite(true, id!!)
        }else{
            bookRoomViewModel.updateFavorite(false, id!!)
            toggle_favorite.isChecked = false
        }
    }

    fun startZeroPagesRead(view: View) {
        bookRoomViewModel.updateReading(0, id!!)
        bookRoomViewModel.updateStatus("Reading", id!!)
    }

    fun finishRead(view: View){
        bookRoomViewModel.updateReading(nPages!!, id!!)
        bookRoomViewModel.updateStatus("Read", id!!)
    }

    fun updatePagesReading(view: View){
        val builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_update_page, null)
        builder.setView(dialogView)

        val editText =
            dialogView.findViewById<View>(R.id.edt_upadate_pages_read) as EditText

        builder.setTitle(R.string.title_aviso)
        builder.setCancelable(true)

            .setPositiveButton(getString(R.string.title_ok)) {  dialog, _ ->
                if (editText.text.toString().equals(""))
                    dialog.cancel()
                else{
                    val value = editText.text.toString()
                    bookRoomViewModel.updateReading(value.toInt(), id!!)
                    Toast.makeText(this, "Páginas atualizadas", Toast.LENGTH_LONG).show()
                }
            }
            // negative button text and action
            .setNegativeButton(getString(R.string.cancelar_alert)) {
                    dialog, _ -> dialog.cancel()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}
