package com.example.ranimaloui.data

import android.content.Context
import com.example.ranimaloui.R

class HeritageRepository(private val context: Context) {

    private val database = AppDatabase.getDatabase(context)
    private val dao = database.questionDao()

    suspend fun getQuestions(category: Category, difficulty: Difficulty): List<Question> {
        populateIfNeeded()
        val entities = dao.getQuestionsByCategoryAndDifficulty(category.name, difficulty.name)
        return entities.map { entity ->
            Question(
                id = entity.id,
                category = category,
                difficulty = difficulty,
                questionText = entity.questionText,
                correctAnswer = entity.correctAnswer,
                imageResName = entity.imageResName
            )
        }
    }

    private suspend fun populateIfNeeded() {
        val count = dao.getQuestionsByCategoryAndDifficulty(Category.ROMAN_HERITAGE.name, Difficulty.EASY.name).size
        if (count == 0) {
            populateRomanHeritage()
        }
    }

    private suspend fun populateRomanHeritage() {
        val questions = listOf(
            // EASY QUESTIONS (5 total)
            QuestionEntity(
                id = 1,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.EASY.name,
                questionText = context.getString(R.string.question_el_jem),
                correctAnswer = context.getString(R.string.answer_el_jem),
                imageResName = "img_el_jem"
            ),
            QuestionEntity(
                id = 2,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.EASY.name,
                questionText = context.getString(R.string.question_dougga),
                correctAnswer = context.getString(R.string.answer_dougga),
                imageResName = "img_dougga"
            ),
            QuestionEntity(
                id = 3,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.EASY.name,
                questionText = context.getString(R.string.question_carthage_baths),
                correctAnswer = context.getString(R.string.answer_carthage_baths),
                imageResName = "img_carthage_baths"
            ),
            QuestionEntity(
                id = 4,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.EASY.name,
                questionText = context.getString(R.string.question_aqueduct),
                correctAnswer = context.getString(R.string.answer_aqueduct),
                imageResName = "img_aqueduct"
            ),
            QuestionEntity(
                id = 13,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.EASY.name,
                questionText = context.getString(R.string.question_thuburbo),
                correctAnswer = context.getString(R.string.answer_thuburbo),
                imageResName = "img_thuburbo"
            ),
            // MEDIUM QUESTIONS (5 total)
            QuestionEntity(
                id = 5,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.MEDIUM.name,
                questionText = context.getString(R.string.question_sbeitla),
                correctAnswer = context.getString(R.string.answer_sbeitla),
                imageResName = "img_sbeitla"
            ),
            QuestionEntity(
                id = 6,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.MEDIUM.name,
                questionText = context.getString(R.string.question_bulla_regia),
                correctAnswer = context.getString(R.string.answer_bulla_regia),
                imageResName = "img_bulla_regia"
            ),
            QuestionEntity(
                id = 7,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.MEDIUM.name,
                questionText = context.getString(R.string.question_uthina),
                correctAnswer = context.getString(R.string.answer_uthina),
                imageResName = "img_uthina"
            ),
            QuestionEntity(
                id = 8,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.MEDIUM.name,
                questionText = context.getString(R.string.question_mactaris),
                correctAnswer = context.getString(R.string.answer_mactaris),
                imageResName = "img_mactaris"
            ),
            QuestionEntity(
                id = 14,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.MEDIUM.name,
                questionText = context.getString(R.string.question_carthage_theater),
                correctAnswer = context.getString(R.string.answer_carthage_theater),
                imageResName = "img_carthage_theater"
            ),
            // HARD QUESTIONS (5 total)
            QuestionEntity(
                id = 9,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.HARD.name,
                questionText = context.getString(R.string.question_chemtou),
                correctAnswer = context.getString(R.string.answer_chemtou),
                imageResName = "img_chemtou"
            ),
            QuestionEntity(
                id = 10,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.HARD.name,
                questionText = context.getString(R.string.question_haidra),
                correctAnswer = context.getString(R.string.answer_haidra),
                imageResName = "img_haidra"
            ),
            QuestionEntity(
                id = 11,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.HARD.name,
                questionText = context.getString(R.string.question_kasserine),
                correctAnswer = context.getString(R.string.answer_kasserine),
                imageResName = "img_kasserine"
            ),
            QuestionEntity(
                id = 12,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.HARD.name,
                questionText = context.getString(R.string.question_timgad),
                correctAnswer = context.getString(R.string.answer_timgad),
                imageResName = "img_timgad_style"
            ),
            QuestionEntity(
                id = 15,
                category = Category.ROMAN_HERITAGE.name,
                difficulty = Difficulty.HARD.name,
                questionText = context.getString(R.string.question_gigthis),
                correctAnswer = context.getString(R.string.answer_gigthis),
                imageResName = "img_gigthis"
            )
        )
        dao.insertQuestions(questions)
    }
}
