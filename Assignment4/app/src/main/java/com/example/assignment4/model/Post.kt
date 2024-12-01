package com.example.assignment4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class Post(
    val userId: Int,
    @PrimaryKey val id: Int,
    val title: String,
    @SerializedName("body") val body: String
)