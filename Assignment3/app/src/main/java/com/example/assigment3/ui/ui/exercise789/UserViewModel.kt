package com.example.assigment3.ui.ui.exercise789

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>().apply {
        value = listOf(
            User("Abzal Issayev", "abzal.issayev@example.com"),
            User("Bill Gates", "bill.gates@example.com"),
            User("Mugivara Luffy", "mugivara.luffy@example.com"),
            User("Donald Trump", "donald.trump@example.com")
        )
    }
    val users: LiveData<List<User>> = _users

    val newUserName = MutableLiveData("")
    val newUserEmail = MutableLiveData("")

    fun addUser() {
        val name = newUserName.value ?: return
        val email = newUserEmail.value ?: return
        if (name.isNotBlank() && email.isNotBlank()) {
            _users.value = _users.value?.plus(User(name, email))
            newUserName.value = ""
            newUserEmail.value = ""
        }
    }
}
