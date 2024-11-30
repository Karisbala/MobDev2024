package com.example.assignment4.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignment4.model.User
import com.example.assignment4.ui.components.UserItem
import com.example.assignment4.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(userViewModel: UserViewModel) {
    val userList by userViewModel.allUsers.collectAsState(initial = emptyList())

    var isDialogOpen by remember { mutableStateOf(false) }
    var userToEdit by remember { mutableStateOf<User?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User List") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                userToEdit = null
                isDialogOpen = true
            }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(userList) { user ->
                UserItem(
                    user = user,
                    onEdit = {
                        userToEdit = it
                        isDialogOpen = true
                    },
                    onDelete = {
                        userViewModel.deleteUser(it)
                    }
                )
            }
        }
    }

    if (isDialogOpen) {
        AddEditUserDialog(
            user = userToEdit,
            onDismiss = { isDialogOpen = false },
            onSave = { user ->
                if (user.id == 0) {
                    userViewModel.insertUser(user)
                } else {
                    userViewModel.updateUser(user)
                }
                isDialogOpen = false
            }
        )
    }
}
