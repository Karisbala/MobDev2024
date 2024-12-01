package com.example.assignment4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment4.data.dao.PostDao
import com.example.assignment4.model.Post

@Database(entities = [Post::class], version = 2)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        @Volatile private var instance: PostDatabase? = null

        fun getDatabase(context: Context): PostDatabase {
            return instance ?: synchronized(this) {
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    PostDatabase::class.java,
                    "app_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                instance = tempInstance
                tempInstance
            }
        }
    }
}
