package com.example.taskNest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.data.TodoEntity
import com.example.mytodo.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class TodoDetailUiState {
    object Loading : TodoDetailUiState()
    data class Success(val todo: TodoEntity) : TodoDetailUiState()
    data class Error(val message: String) : TodoDetailUiState()
}

class TodoDetailViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<TodoDetailUiState>(TodoDetailUiState.Loading)
    val uiState: StateFlow<TodoDetailUiState> = _uiState

    fun loadTodo(id: Int) {
        _uiState.value = TodoDetailUiState.Loading
        viewModelScope.launch {
            try {
                val todo = repository.getTodoById(id)
                if (todo != null) {
                    _uiState.value = TodoDetailUiState.Success(todo)
                } else {
                    _uiState.value = TodoDetailUiState.Error("Todo not found")
                }
            } catch (e: Exception) {
                _uiState.value = TodoDetailUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
} 