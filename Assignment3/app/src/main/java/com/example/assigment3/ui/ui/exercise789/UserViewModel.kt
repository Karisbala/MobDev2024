package com.example.assigment3.ui.ui.exercise789

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userDao: UserDao) : ViewModel() {
    val users: LiveData<List<User>> = userDao.getAllUsers()

    val newUserName = MutableLiveData("")
    val newUserEmail = MutableLiveData("")

    fun addUser() {
        val name = newUserName.value ?: return
        val email = newUserEmail.value ?: return
        if (name.isNotBlank() && email.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                userDao.insert(User(name = name, email = email))
                withContext(Dispatchers.Main) {
                    newUserName.value = ""
                    newUserEmail.value = ""
                }
            }
        }
    }

    companion object {
        fun provideFactory(userDao: UserDao): androidx.lifecycle.ViewModelProvider.Factory =
            object : androidx.lifecycle.ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return UserViewModel(userDao) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }
}