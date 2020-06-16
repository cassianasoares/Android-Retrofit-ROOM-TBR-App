package com.demo.android.cassianasoares.room.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book")
data class Book (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "book_id")
    val uuid: String,
    @ColumnInfo(name = "book_name")
    var name: String,
    var authors: String,
    var url_image: String,
    var n_pages: Int,
    var n_read_pages: Int,
    var status: String,
    @ColumnInfo(name = "book_note")
    var note: Int,
    var favorite: Boolean
)