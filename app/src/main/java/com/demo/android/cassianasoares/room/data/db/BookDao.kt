package com.demo.android.cassianasoares.room.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo.android.cassianasoares.room.data.model.Book


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Query("SELECT * FROM book")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE status=:status")
    fun getAllBooksByStatus(status: String): LiveData<List<Book>>
    @Query("SELECT * FROM book WHERE book_id=:id")
    fun getBook(id: String): LiveData<Book>

    @Query("UPDATE book SET status = :status WHERE book_id=:id")
    fun updateStatus(status: String, id: String)

    @Query("UPDATE book SET favorite = :favorite WHERE book_id=:id")
    fun updateFavorite(favorite: Boolean, id: String)

    @Query("UPDATE book SET book_note = :note WHERE book_id=:id")
    fun updateNote(note:Int, id: String)

    @Query("UPDATE book SET n_read_pages = :n_read_pages WHERE book_id=:id")
    fun updatePageRead(n_read_pages: Int, id: String)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

}