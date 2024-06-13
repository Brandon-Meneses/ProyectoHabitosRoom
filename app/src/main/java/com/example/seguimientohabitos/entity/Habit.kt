package com.example.seguimientohabitos.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val progress: Int = 0 // Percentage of completion
)