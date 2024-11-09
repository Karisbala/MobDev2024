package com.example.assigment3.ui.ui.exercise789

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UserListScreen(users: List<User>, userViewModel: UserViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        UserInputFields(userViewModel)
        Spacer(modifier = Modifier.height(16.dp))
        UserList(users = users)
    }
}

@Composable
fun UserInputFields(userViewModel: UserViewModel) {
    val name = userViewModel.newUserName.observeAsState("")
    val email = userViewModel.newUserEmail.observeAsState("")

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = name.value,
            onValueChange = { userViewModel.newUserName.value = it },
            label = { Text("Enter name") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = email.value,
            onValueChange = { userViewModel.newUserEmail.value = it },
            label = { Text("Enter email") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        Button(onClick = { userViewModel.addUser() }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Add User")
        }
    }
}


@Composable
fun UserList(users: List<User>) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(users) { user ->
            UserItem(user = user) {
                Toast.makeText(context, "Clicked on: ${user.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun UserItem(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
