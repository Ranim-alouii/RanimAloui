package com.example.ranimaloui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ranimaloui.data.Category
import com.example.ranimaloui.data.Difficulty
import com.example.ranimaloui.data.HeritageRepository
import com.example.ranimaloui.data.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Data class representing the UI state of the Quiz
 */
data class QuizUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswer: String? = null,
    val score: Int = 0,
    val isQuizFinished: Boolean = false,
    val isLoading: Boolean = false
)

class QuizViewModel(private val repository: HeritageRepository) : ViewModel() {

    private val _quizState = MutableStateFlow(QuizUiState())
    val quizState: StateFlow<QuizUiState> = _quizState.asStateFlow()

    /**
     * Loads questions based on category and difficulty
     */
    fun loadQuestions(category: Category, difficulty: Difficulty) {
        viewModelScope.launch {
            _quizState.update { it.copy(isLoading = true) }
            val questions = repository.getQuestions(category, difficulty)
            _quizState.update {
                it.copy(
                    questions = questions,
                    isLoading = false,
                    currentQuestionIndex = 0,
                    selectedAnswer = null,
                    isQuizFinished = false
                )
            }
        }
    }

    /**
     * Updates the state when a user selects an answer
     * This matches the call in QuizViewModelAndroidTest.kt
     */
    fun onAnswerSelected(answer: String) {
        _quizState.update { currentState ->
            currentState.copy(selectedAnswer = answer)
        }
    }

    /**
     * Resets the quiz state
     */
    fun resetQuiz() {
        _quizState.update { QuizUiState() }
    }
}