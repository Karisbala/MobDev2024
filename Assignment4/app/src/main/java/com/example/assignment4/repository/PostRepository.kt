package com.example.assignment4.repository

import com.example.assignment4.data.dao.PostDao
import com.example.assignment4.model.Post
import com.example.assignment4.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(
    private val apiService: ApiService,
    private val postDao: PostDao
) {

    suspend fun fetchPosts(): Result<List<Post>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPosts()
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        postDao.insertPosts(posts)
                        Result.success(posts)
                    } ?: Result.failure(Exception("No data"))
                } else {
                    Result.failure(Exception("Error ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getCachedPosts(): List<Post> {
        return withContext(Dispatchers.IO) {
            postDao.getAllPosts()
        }
    }
}