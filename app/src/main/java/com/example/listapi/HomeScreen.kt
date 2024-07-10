package com.example.listapi

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listapi.ViewModel.TodoViewModel
import com.example.listapi.repository.Repository
import com.example.listapi.room.TodoDB
import com.example.listapi.screens.MainScreen

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val db = TodoDB.getInstance(context)
    val repository = Repository(db)
    val myViewModel = TodoViewModel(repository)
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Main") {
        composable("Main") { MainScreen(viewModel = myViewModel, navController)}
        composable("Card/{todoId}/{todoName}") {
            CardScreen(
                navController = navController,
                viewModel = myViewModel,
                todoId = it.arguments?.getString("todoId"),
                todoName = it.arguments?.getString("todoName"),
            )}

    }
}