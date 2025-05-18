package com.example.taskNest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.data.TodoEntity
import com.example.mytodo.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class TodoListUiState {
    object Loading : TodoListUiState()
    data class Success(val todos: List<TodoEntity>) : TodoListUiState()
    data class Error(val message: String) : TodoListUiState()
}

class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<TodoListUiState>(TodoListUiState.Loading)
    val uiState: StateFlow<TodoListUiState> = _uiState

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        _uiState.value = TodoListUiState.Loading
        viewModelScope.launch {
            try {
                val todos = repository.getTodos()
                _uiState.value = TodoListUiState.Success(todos)
            } catch (e: Exception) {
                _uiState.value = TodoListUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
} 