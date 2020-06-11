package com.demo.android.cassianasoares.room.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo.android.cassianasoares.room.data.Book


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Query("SELECT * FROM book WHERE status=:status")
    fun getAllBooksByStatus(status: String): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE book_id=:id")
    fun getBook(id: String): LiveData<Book>

    /*@Query("UPDATE book SET status = :status WHERE book_id=:id")
    fun updateStatus(status: String, id: String): LiveData<Book>

    @Query("UPDATE book SET book_note = :note WHERE book_id=:id")
    fun updateNote(note: String, id: String): LiveData<Book>

    @Query("UPDATE book SET favorite = :favorite WHERE book_id=:id")
    fun updateFavorite(favorite: Boolean, id: String): LiveData<Book>

    @Query("UPDATE book SET n_pages = :n_pages WHERE book_id=:id")
    fun updatePageRead(n_pages: Boolean, id: String): LiveData<Book>*/

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

}