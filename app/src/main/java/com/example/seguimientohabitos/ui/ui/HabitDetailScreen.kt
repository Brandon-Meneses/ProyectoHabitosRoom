package com.example.seguimientohabitos.ui.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.seguimientohabitos.viewmodel.HabitViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitDetailScreen(habitId: Int, viewModel: HabitViewModel, navController: NavHostController) {
    val habit by viewModel.getHabit(habitId).collectAsState(initial = null)
    var progress by remember { mutableStateOf(0) }

    LaunchedEffect(habit) {
        habit?.let {
            progress = it.progress
        }
    }

    habit?.let { currentHabit ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentHabit.name) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            viewModel.deleteHabit(currentHabit)
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete Habit")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    viewModel.updateHabitProgress(habitId, progress)
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.Done, contentDescription = "Save Progress")
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Track your habit progress")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Current Progress: $progress%")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = progress.toString(),
                    onValueChange = { value -> progress = value.toIntOrNull() ?: 0 },
                    label = { Text("Progress (%)") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


