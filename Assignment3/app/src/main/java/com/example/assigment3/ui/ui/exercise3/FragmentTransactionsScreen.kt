package com.example.assigment3.ui.ui.exercise3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FragmentTransactionsScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "firstFragment") {
        composable("firstFragment") { FirstFragmentComposable(navController) }
        composable("secondFragment") { SecondFragmentComposable(navController) }
    }
}

@Composable
fun FirstFragmentComposable(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "This is the First Fragment (Abzal1)",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                navController.navigate("secondFragment") {
                    launchSingleTop = true
                    popUpTo("secondFragment")
                }
            }
        ) {
            Text("Go to Second Fragment")
        }
    }
}

@Composable
fun SecondFragmentComposable(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This is the Second Fragment (Abzal2     )",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                navController.navigate("firstFragment") {
                    launchSingleTop = true
                    popUpTo("firstFragment")
                }
            }
        ) {
            Text("Go to First Fragment")
        }
    }
}
