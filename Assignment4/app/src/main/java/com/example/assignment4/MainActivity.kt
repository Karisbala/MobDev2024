package com.example.assignment4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.assignment4.data.AppDatabase
import com.example.assignment4.data.PostDatabase
import com.example.assignment4.network.ApiClient
import com.example.assignment4.network.ApiService
import com.example.assignment4.repository.PostRepository
import com.example.assignment4.repository.UserRepository
import com.example.assignment4.ui.screens.PostListScreen
import com.example.assignment4.ui.screens.UserListScreen
import com.example.assignment4.ui.theme.Assignment4Theme
import com.example.assignment4.viewmodel.PostViewModel
import com.example.assignment4.viewmodel.PostViewModelFactory
import com.example.assignment4.viewmodel.UserViewModel
import com.example.assignment4.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    private val apiService = ApiClient.createService(ApiService::class.java)

    //private val database by lazy { AppDatabase.getDatabase(this) }
    private val database by lazy { PostDatabase.getDatabase(this) }

    //private val repository by lazy { UserRepository(database.userDao()) }
    private val repository by lazy { PostRepository(apiService, database.postDao()) }


//    private val userViewModel: UserViewModel by viewModels {
//        UserViewModelFactory(repository)
//    }
    private val postViewModel: PostViewModel by viewModels {
        PostViewModelFactory(repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment4Theme {
                //UserListScreen(userViewModel = userViewModel)
                PostListScreen(postViewModel)
            }
        }
    }
}