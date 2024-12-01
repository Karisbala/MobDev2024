package com.example.assignment4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.assignment4.model.Post
import com.example.assignment4.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun loadPosts() {
        viewModelScope.launch {
            val result = repository.fetchPosts()
            if (result.isSuccess) {
                _posts.value = result.getOrDefault(emptyList())
            } else {
                _error.value = result.exceptionOrNull()?.message
                // Load cached posts
                _posts.value = repository.getCachedPosts()
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class PostViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(repository) as T
    }
}