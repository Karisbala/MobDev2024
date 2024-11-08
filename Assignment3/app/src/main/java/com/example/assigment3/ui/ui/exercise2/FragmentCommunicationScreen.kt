package com.example.assigment3.ui.ui.exercise2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FragmentCommunicationScreen(sharedViewModel: SharedViewModel = viewModel()) {
    if (sharedViewModel.showOutput) {
        OutputComposable(modifier = Modifier.fillMaxSize(), sharedViewModel)
    } else {
        InputComposable(modifier = Modifier.fillMaxSize(), sharedViewModel) {
            sharedViewModel.onSubmit()
        }
    }
}

@Composable
fun InputComposable(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    onSubmit: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "InputComposable",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = sharedViewModel.inputText,
            onValueChange = { sharedViewModel.inputText = it },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { onSubmit() }) {
            Text("Submit")
        }
    }
}

@Composable
fun OutputComposable(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "OutputComposable",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = sharedViewModel.inputText,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}