package com.example.assignment4

import com.example.assignment4.data.dao.PostDao
import com.example.assignment4.model.Post
import com.example.assignment4.network.ApiService
import com.example.assignment4.repository.PostRepository
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostRepositoryTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService
    private lateinit var repository: PostRepository

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        repository = PostRepository(apiService, FakePostDao())
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetchPosts returns posts on success`() = runBlocking {
        val mockResponse = MockResponse()
        mockResponse.setBody(
            """
            [
                {"userId": 1, "id": 1, "title": "Test Title", "body": "Test Body"}
            ]
            """
        )
        mockWebServer.enqueue(mockResponse)

        val result = repository.fetchPosts()
        assertTrue(result.isSuccess)
        val posts = result.getOrNull()
        assertNotNull(posts)
        assertEquals(1, posts?.size)
        assertEquals("Test Title", posts?.first()?.title)
    }
}

class FakePostDao : PostDao {
    private val posts = mutableListOf<Post>()

    override suspend fun getAllPosts(): List<Post> {
        return posts
    }

    override suspend fun insertPosts(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
    }
}
