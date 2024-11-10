package com.example.assigment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assigment3.ui.theme.Assigment3Theme
import com.example.assigment3.ui.ui.exercise789.UserDatabase
import com.example.assigment3.ui.ui.exercise789.UserListScreen
import com.example.assigment3.ui.ui.exercise789.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = UserDatabase.getDatabase(this)
            val userDao = db.userDao()
            val userViewModel: UserViewModel = viewModel(factory = UserViewModel.provideFactory(userDao))
            val users = userViewModel.users.observeAsState(listOf())
            Assigment3Theme {
                // BasicFragmentScreen("Hello from Compose!") // Exercise 1
                // FragmentCommunicationScreen() // Exercise 2
                // FragmentTransactionsScreen() // Exercise 3
                // MovieList(movies = resources.getStringArray(R.array.movie_list)) // Exercise 456
                UserListScreen(users = users.value, userViewModel = userViewModel) // Exercise 789
            }
        }
    }
}