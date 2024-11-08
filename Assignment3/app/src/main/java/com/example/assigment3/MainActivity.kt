package com.example.assigment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.assigment3.ui.theme.Assigment3Theme
import com.example.assigment3.ui.ui.exercise1.BasicFragmentScreen
import com.example.assigment3.ui.ui.exercise2.FragmentCommunicationScreen
import com.example.assigment3.ui.ui.exercise3.FragmentTransactionsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assigment3Theme {
                // BasicFragmentScreen("Hello from Compose!") // Exercise 1
                // FragmentCommunicationScreen() // Exercise 2
                FragmentTransactionsScreen() // Exercise 3
            }
        }
    }
}