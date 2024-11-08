package com.example.assigment3.ui.ui.exercise1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun BasicFragmentScreen(message: String) {
    val lifecycleOwner = rememberUpdatedState(newValue = LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> println("Composable: onCreate")
                Lifecycle.Event.ON_START -> println("Composable: onStart")
                Lifecycle.Event.ON_RESUME -> println("Composable: onResume")
                Lifecycle.Event.ON_PAUSE -> println("Composable: onPause")
                Lifecycle.Event.ON_STOP -> println("Composable: onStop")
                Lifecycle.Event.ON_DESTROY -> println("Composable: onDestroy")
                else -> {}
            }
        }
        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
    Text(text = message)
}