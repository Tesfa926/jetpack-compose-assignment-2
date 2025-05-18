package com.example.mytodo.repository

import com.example.mytodo.data.TodoDao
import com.example.mytodo.data.TodoEntity
import com.example.mytodo.model.TodoItem
import com.example.mytodo.network.TodoApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(
    private val todoDao: TodoDao,
    private val apiService: TodoApiService
) {
    suspend fun getTodos(): List<TodoEntity> = withContext(Dispatchers.IO) {
        val cached = todoDao.getAllTodos()
        try {
            val remote = apiService.getTodos()
            val entities = remote.map { it.toEntity() }
            todoDao.insertTodos(entities)
            entities
        } catch (e: Exception) {
            cached
        }
    }

    suspend fun getTodoById(id: Int): TodoEntity? = withContext(Dispatchers.IO) {
        todoDao.getTodoById(id)
    }
}

fun TodoItem.toEntity() = TodoEntity(
    id = id,
    userId = userId,
    title = title,
    completed = completed
) 