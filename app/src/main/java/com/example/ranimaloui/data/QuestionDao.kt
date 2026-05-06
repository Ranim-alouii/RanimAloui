package com.example.ranimaloui.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE category = :category AND difficulty = :difficulty")
    suspend fun getQuestionsByCategoryAndDifficulty(category: String, difficulty: String): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)
}
