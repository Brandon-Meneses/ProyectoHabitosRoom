package com.example.seguimientohabitos.ui.navmanager

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seguimientohabitos.ui.ui.AddHabitScreen
import com.example.seguimientohabitos.ui.ui.HabitDetailScreen
import com.example.seguimientohabitos.ui.ui.HabitListScreen
import com.example.seguimientohabitos.viewmodel.HabitViewModel

@Composable
fun HabitApp() {
    val navController = rememberNavController()
    val viewModel: HabitViewModel = viewModel()

    NavHost(navController = navController, startDestination = "habit_list") {
        composable("habit_list") {
            HabitListScreen(navController, viewModel)
        }
        composable("add_habit") {
            AddHabitScreen(navController, viewModel)
        }
        composable("habit_detail/{habitId}") { backStackEntry ->
            val habitId = backStackEntry.arguments?.getString("habitId")?.toInt() ?: return@composable
            HabitDetailScreen(habitId, viewModel, navController)
        }
    }
}
