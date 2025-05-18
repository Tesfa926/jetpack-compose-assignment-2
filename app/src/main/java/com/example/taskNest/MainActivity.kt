package com.example.taskNest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.mytodo.data.AppDatabase
import com.example.mytodo.network.RetrofitInstance
import com.example.mytodo.repository.TodoRepository
import com.example.taskNest.ui.TodoNavGraph
import com.example.mytodo.ui.theme.MyTodoTheme
import com.example.taskNest.viewmodel.TodoDetailViewModel
import com.example.taskNest.viewmodel.TodoListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set up Room database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "todo-db"
        ).build()
        val todoDao = db.todoDao()

        // Set up Retrofit
        val apiService = RetrofitInstance.api

        // Set up Repository
        val repository = TodoRepository(todoDao, apiService)

        // Set up ViewModels
        val listViewModel = TodoListViewModel(repository)
        val detailViewModel = TodoDetailViewModel(repository)

        setContent {
            MyTodoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoNavGraph(
                        listViewModel = listViewModel,
                        detailViewModel = detailViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}