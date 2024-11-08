package com.example.assigment3.ui.ui.exercise2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var inputText by mutableStateOf("")
    var showOutput by mutableStateOf(false)

    fun onSubmit() {
        showOutput = true
    }
}