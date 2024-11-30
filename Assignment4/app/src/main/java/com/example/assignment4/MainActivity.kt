package com.example.assignment4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.assignment4.data.AppDatabase
import com.example.assignment4.repository.UserRepository
import com.example.assignment4.viewmodel.UserViewModel
import com.example.assignment4.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { UserRepository(database.userDao()) }

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}