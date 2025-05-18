package com.example.taskNest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskNest.viewmodel.TodoDetailUiState
import com.example.taskNest.viewmodel.TodoDetailViewModel
import com.example.mytodo.data.TodoEntity

@Composable
fun TodoDetailScreen(
    viewModel: TodoDetailViewModel,
    todoId: Int,
    onBack: () -> Unit
) {

        val uiState by viewModel.uiState.collectAsState()
        LaunchedEffect(todoId) { viewModel.loadTodo(todoId) }
        when (uiState) {
            is TodoDetailUiState.Loading -> LoadingView()
            is TodoDetailUiState.Error -> ErrorView(
                (uiState as TodoDetailUiState.Error).message,
                onBack
            )

            is TodoDetailUiState.Success -> TodoDetail(
                todo = (uiState as TodoDetailUiState.Success).todo,
                onBack = onBack
            )
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetail(todo: TodoEntity, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50)
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0ECC3)) // Background for the full screen
        ) {
            Column(
                modifier = Modifier
                    .padding(padding) // Scaffold padding
                    .padding(horizontal = 16.dp) ,// Additional space
                verticalArrangement = Arrangement.Top
            ) {
                CompositionLocalProvider(
                    LocalTextStyle provides LocalTextStyle.current.copy(color = Color(0xFF000000))
                ) {
                    Row{
                        Text(text = "Title: ",
                        fontWeight = FontWeight.Bold
                        )
                        Text(text = todo.title)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row{
                        Text(text = "Completed: ",
                            fontWeight = FontWeight.Bold)
                        Text(text = if (todo.completed) "Yes" else "No")
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row{
                        Text(text = "User ID: ",
                            fontWeight = FontWeight.Bold)
                        Text(text = "${todo.userId}")
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row{
                        Text(text = "Todo ID: ",
                            fontWeight = FontWeight.Bold)
                        Text(text = "${todo.id}")
                    }

                }
            }
        }
    }
}