package com.example.ranimaloui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ranimaloui.data.Category
import com.example.ranimaloui.data.Difficulty
import com.example.ranimaloui.data.HeritageRepository
import com.example.ranimaloui.data.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * QuizUiState represents the state of the Quiz screen.
 * Requirement: State management (15 pts).
 */
data class QuizUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswer: String? = null,
    val score: Int = 0,
    val timeLeft: Int = 15,
    val isAnswered: Boolean = false,
    val isCorrect: Boolean? = null,
    val isLoading: Boolean = false,
    val isFinished: Boolean = false
)

class QuizViewModel(private val repository: HeritageRepository) : ViewModel() {

    private val _quizState = MutableStateFlow(QuizUiState())
    val quizState: StateFlow<QuizUiState> = _quizState.asStateFlow()

    /**
     * Loads questions and resets the quiz state.
     */
    fun loadQuestions(category: Category, difficulty: Difficulty) {
        viewModelScope.launch {
            _quizState.update { it.copy(isLoading = true) }
            val questions = repository.getQuestions(category, difficulty)
            _quizState.update {
                it.copy(
                    questions = questions,
                    currentQuestionIndex = 0,
                    score = 0,
                    timeLeft = 15,
                    isAnswered = false,
                    isCorrect = null,
                    isLoading = false,
                    isFinished = false
                )
            }
            startTimer()
        }
    }

    /**
     * Starts the countdown timer for the current question.
     * Requirement: 15-second timer logic.
     */
    private fun startTimer() {
        viewModelScope.launch {
            // Ensure timer starts at 15 for each question
            _quizState.update { it.copy(timeLeft = 15) }
            
            while (_quizState.value.timeLeft > 0 && !_quizState.value.isAnswered) {
                delay(1000)
                _quizState.update { it.copy(timeLeft = it.timeLeft - 1) }
            }
            
            // If time is up and no answer was selected, mark as incorrect
            if (!_quizState.value.isAnswered && _quizState.value.timeLeft == 0) {
                _quizState.update { it.copy(isAnswered = true, isCorrect = false) }
            }
        }
    }

    /**
     * Logic for answer selection and scoring.
     * Requirement: MCQ scoring (1 pt per correct answer).
     */
    fun onAnswerSelected(answer: String) {
        if (_quizState.value.isAnswered) return
        
        val currentQuestion = _quizState.value.questions[_quizState.value.currentQuestionIndex]
        val isCorrect = answer == currentQuestion.correctAnswer
        
        _quizState.update {
            it.copy(
                selectedAnswer = answer,
                isAnswered = true,
                isCorrect = isCorrect,
                score = if (isCorrect) it.score + 1 else it.score
            )
        }
    }

    /**
     * Advances to the next question or sets isFinished to true.
     */
    fun nextQuestion() {
        val nextIndex = _quizState.value.currentQuestionIndex + 1
        if (nextIndex < _quizState.value.questions.size) {
            _quizState.update { currentState ->
                currentState.copy(
                    currentQuestionIndex = nextIndex,
                    selectedAnswer = null,
                    isAnswered = false,
                    isCorrect = null,
                    timeLeft = 15
                )
            }
            startTimer()
        } else {
            _quizState.update { it.copy(isFinished = true) }
        }
    }
}
