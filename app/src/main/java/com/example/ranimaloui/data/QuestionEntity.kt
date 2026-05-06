package com.example.ranimaloui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id: Int,
    val category: String,
    val difficulty: String,
    val questionText: String,
    val correctAnswer: String,
    val imageResName: String
)
