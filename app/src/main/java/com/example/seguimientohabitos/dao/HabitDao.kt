package com.example.seguimientohabitos.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seguimientohabitos.entity.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM Habit")
    fun getAll(): Flow<List<Habit>>

    @Query("SELECT * FROM Habit WHERE id = :habitId")
    fun getHabit(habitId: Int): Flow<Habit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)
}
