package com.demo.android.cassianasoares

import android.app.Application
import androidx.room.Room
import com.demo.android.cassianasoares.room.data.db.BookDatabase

class BookApplication: Application() {

    companion object{
        lateinit var database: BookDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, BookDatabase::class.java, "book_database")
            .build()
    }

}