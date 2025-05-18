package com.example.mytodo.network

import com.example.mytodo.model.TodoItem
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<TodoItem>
} 