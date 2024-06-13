package com.example.seguimientohabitos.repository

import com.example.seguimientohabitos.dao.HabitDao
import com.example.seguimientohabitos.entity.Habit
import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {
    val habits: Flow<List<Habit>> = habitDao.getAll()

    fun getHabit(habitId: Int): Flow<Habit> = habitDao.getHabit(habitId)

    suspend fun insert(habit: Habit) {
        habitDao.insert(habit)
    }

    suspend fun delete(habit: Habit) {
        habitDao.delete(habit)
    }
}
