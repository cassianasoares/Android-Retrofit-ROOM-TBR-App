package com.demo.android.cassianasoares.room.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo.android.cassianasoares.room.data.Book


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Query("SELECT * FROM book WHERE status=:status")
    fun getAllReadingBooks(status: String): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE status=:status")
    fun getAllReadBooks(status: String): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE status=:status")
    fun getAllToReadBooks(status: String): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE book_id=:id")
    fun getBook(id: String): LiveData<Book>

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

}