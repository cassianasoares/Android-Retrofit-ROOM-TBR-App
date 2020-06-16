package com.demo.android.cassianasoares.room.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.android.cassianasoares.room.data.model.Book


@Database(entities = arrayOf(Book::class), version = 1)
abstract class BookDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

}