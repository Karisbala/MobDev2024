package com.example.assigment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.assigment3.ui.theme.Assigment3Theme
import com.example.assigment3.ui.ui.exercise1.BasicFragmentScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assigment3Theme {
                BasicFragmentScreen("Hello from Compose!")
            }
        }
    }
}