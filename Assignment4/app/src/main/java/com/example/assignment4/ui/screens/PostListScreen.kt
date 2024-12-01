package com.example.assignment4.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.assignment4.ui.components.PostItem
import com.example.assignment4.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(postViewModel: PostViewModel) {
    val posts by postViewModel.posts.collectAsState()
    val error by postViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        postViewModel.loadPosts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Posts") }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (error != null) {
                Text(text = "Error: $error", color = MaterialTheme.colorScheme.error)
            }
            LazyColumn {
                items(posts) { post ->
                    PostItem(post = post, onClick = {
                        // Handle post click
                    })
                }
            }
        }
    }
}
