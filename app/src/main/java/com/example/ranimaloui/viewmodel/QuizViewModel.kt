package com.example.ranimaloui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ranimaloui.data.Category
import com.example.ranimaloui.data.Difficulty
import com.example.ranimaloui.data.HeritageRepository
import com.example.ranimaloui.data.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Full state required by QuizScreen.kt
 */
data class QuizUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0, // FIXED: Added for image errors
    val selectedAnswer: String? = null,
    val score: Int = 0,               // FIXED: Added for image errors
    val timeLeft: Int = 30,           // FIXED: Added for image errors
    val isAnswered: Boolean = false,  // FIXED: Added for image errors
    val isLoading: Boolean = false
)

class QuizViewModel(private val repository: HeritageRepository) : ViewModel() {

    private val _quizState = MutableStateFlow(QuizUiState())
    val quizState: StateFlow<QuizUiState> = _quizState.asStateFlow()

    fun loadQuestions(category: Category, difficulty: Difficulty) {
        val questions = repository.getQuestions(category, difficulty)
        _quizState.update {
            it.copy(
                questions = questions,
                currentQuestionIndex = 0,
                score = 0,
                isAnswered = false
            )
        }
    }

    fun onAnswerSelected(answer: String) {
        _quizState.update {
            it.copy(
                selectedAnswer = answer,
                isAnswered = true
            )
        }
    }

    // FIXED: Added method for the UI's "Next" button
    fun nextQuestion() {
        _quizState.update { currentState ->
            currentState.copy(
                currentQuestionIndex = currentState.currentQuestionIndex + 1,
                selectedAnswer = null,
                isAnswered = false
            )
        }
    }
}