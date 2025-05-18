package com.example.taskNest.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskNest.viewmodel.TodoDetailViewModel
import com.example.taskNest.viewmodel.TodoListViewModel

@Composable
fun TodoNavGraph(
    listViewModel: TodoListViewModel,
    detailViewModel: TodoDetailViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome",
        modifier = modifier
    ) {
        composable("welcome") {
            WelcomeScreen(onGetStarted = { navController.navigate("list") })
        }
        composable("list") {
            TodoListScreen(
                viewModel = listViewModel,
                onTodoClick = { id -> navController.navigate("detail/$id") },
                onBack = { navController.popBackStack() }
            )
        }
        composable("detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: return@composable
            TodoDetailScreen(
                viewModel = detailViewModel,
                todoId = todoId,
                onBack = { navController.popBackStack() }
            )
        }
    }
} 