package com.example.seguimientohabitos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.seguimientohabitos.database.AppDatabase
import com.example.seguimientohabitos.entity.Habit
import com.example.seguimientohabitos.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    private val habitDao = AppDatabase.getDatabase(application).habitDao()
    private val repository = HabitRepository(habitDao)

    val habits: StateFlow<List<Habit>> = repository.habits.stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    fun getHabit(habitId: Int): Flow<Habit> = repository.getHabit(habitId)

    fun addHabit(habit: Habit) = viewModelScope.launch {
        repository.insert(habit)
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch {
        repository.delete(habit)
    }

    fun updateHabitProgress(habitId: Int, progress: Int) = viewModelScope.launch {
        val habit = repository.getHabit(habitId).firstOrNull()
        if (habit != null) {
            repository.insert(habit.copy(progress = progress))
        }
    }
}

