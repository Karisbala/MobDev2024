package com.example.assignment4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id") val id: Int = 0,

    @ColumnInfo(name = "first_name") val firstName: String,

    @ColumnInfo(name = "last_name") val lastName: String,

    @ColumnInfo(name = "email") val email: String
)
