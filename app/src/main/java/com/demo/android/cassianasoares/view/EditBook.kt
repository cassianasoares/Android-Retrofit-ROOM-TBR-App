package com.demo.android.cassianasoares.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.demo.android.cassianasoares.R
import com.demo.android.cassianasoares.room.data.model.Book
import com.demo.android.cassianasoares.room.data.Repository
import com.demo.android.cassianasoares.viewModel.EditBookViewModel
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBook : AppCompatActivity() {

    private val editBookViewModel by lazy { EditBookViewModel(Repository()) }
    var id: String = ""
    var pages_read: String? = null
    var pages_total: String? = null
    var bookGet: Book? = null
    var name_book: String? = null
    var name_author: String? = null
    var status: String? = null
    var url_image: String? = null
    var n_lidas: Int = 0
    var n_total: Int = 0
    var book_note: Int? = null
    var favorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)
        id = intent.getStringExtra("BOOKID")!!
        editBookViewModel.getBook(id).observe(this, Observer
        {                books ->
            books.let { getBook(it) }
        })
    }

    fun getBook(book: Book){

            if (book != null) {

            bookGet = book
            //Snipper Config
            val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this,
                R.array.status, android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_status.adapter = adapter

            if(book.status == "Reading"){
                spinner_status.setSelection(0)
                edt_n_read_pages.setText(book.n_read_pages.toString())
            }else if(book.status == "To Read"){
                spinner_status.setSelection(1)
                txt_n_read_pages.visibility = View.GONE
                edt_n_read_pages.visibility = View.GONE
            }else{
                spinner_status.setSelection(2)
                edt_n_read_pages.setText(book.n_read_pages.toString())
            }

            //hange Status
            spinner_status.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    status = parent!!.getItemAtPosition(position).toString()
                    if (status == "Reading" || status == "Read"){
                        txt_n_read_pages.visibility = View.VISIBLE
                        edt_n_read_pages.visibility = View.VISIBLE
                    }else{
                        txt_n_read_pages.visibility = View.GONE
                        edt_n_read_pages.visibility = View.GONE
                    }
                }
            }

                edt_url_image.setText(book.url_image)
                edt_name.setText(book.name)
                edt_author.setText(book.authors)
                edt_n_pages.setText(book.n_pages.toString())

                checkBox_favorite.isChecked = book.favorite == true

                checkBox_favorite.setOnClickListener {
                    favorite = checkBox_favorite.isChecked
                }

                book_note = book.note

        }
    }

    fun alterBook(view: View) {
        if (!validaCampos()) {
            if (n_lidas == n_total){
                status = "Read"
            }
                val bookSelect =
                    Book(
                        id, name_book!!, name_author!!, url_image!!, n_total,
                        n_lidas, status!!, book_note!!, favorite
                    )
                editBookViewModel.updateBook(bookSelect)
            Toast.makeText(this, "Livro atualizado com sucesso!", Toast.LENGTH_LONG).show()
            this.finish()
        }else{
            Toast.makeText(this, "Erro ao tentar atualizar", Toast.LENGTH_LONG).show()
        }
    }


    fun trimStartAndEnd(value: String): String{

        val starTrim = value.trimStart()
        val endTrim = starTrim.trimEnd()
        return endTrim

    }


    private fun validaCampos(): Boolean {

        var res = false
        var invalido = false
        name_book = trimStartAndEnd(edt_name!!.text.toString())
        name_author = trimStartAndEnd(edt_author!!.text.toString())
        pages_read = trimStartAndEnd(edt_n_read_pages!!.text.toString())
        pages_total = trimStartAndEnd(edt_n_pages!!.text.toString())
        url_image = trimStartAndEnd(edt_url_image!!.text.toString())

        if (edt_n_read_pages.isVisible && pages_read != ""){
            n_lidas= Integer.parseInt(pages_read!!)
        }else{
            n_lidas = bookGet!!.n_read_pages
        }

        n_total = Integer.parseInt(pages_total!!)


        if (isCampoVazio(name_book!!)) {
            res = true
            edt_name!!.requestFocus()
        } else if (isCampoVazio(name_author!!)) {
            res = true
            edt_author!!.requestFocus()
        } else if (isCampoVazio(pages_total!!)) {
            res = true
            edt_n_pages!!.requestFocus()
        }else if (edt_n_read_pages.isVisible && isCampoVazio(pages_read!!)) {
            res = true
            edt_n_read_pages!!.requestFocus()
        }else if (isCampoVazio(url_image!!)) {
            res = true
            edt_url_image!!.requestFocus()
        }

        if(n_lidas > n_total){
            invalido = true
            edt_n_read_pages!!.requestFocus()
        }

        if (invalido) {
            res = true

            val dig = AlertDialog.Builder(this)
            dig.setTitle(R.string.title_aviso)
            dig.setMessage(getString(R.string.msg_lidas_maior_total))
            dig.setNeutralButton(R.string.title_ok, null)
            dig.show()

        }else if (res) {

            val dig = AlertDialog.Builder(this)
            dig.setTitle(R.string.title_aviso)
            dig.setMessage(R.string.massage_campos_invalidos_brancos)
            dig.setNeutralButton(R.string.title_ok, null)
            dig.show()
        }

        return res
    }


    private fun isCampoVazio(valor: String): Boolean {
        return TextUtils.isEmpty(valor) || valor.trim { it <= ' ' }.isEmpty()
    }
}
