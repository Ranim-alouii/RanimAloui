package com.example.ranimaloui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ranimaloui.viewmodel.QuizViewModel
import com.example.ranimaloui.data.HeritageRepository
import com.example.ranimaloui.data.Category
import com.example.ranimaloui.data.Difficulty
import com.example.ranimaloui.data.Question
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class QuizViewModelAndroidTest {

    private lateinit var repository: HeritageRepository
    private lateinit var viewModel: QuizViewModel

    @Before
    fun setup() {
        repository = mock(HeritageRepository::class.java)
        viewModel = QuizViewModel(repository)
    }

    @Test
    fun testViewModelUpdatesStateOnAnswerSelect() = runTest {
        // FIXED: Using correct types (Enums for category/difficulty) and named arguments
        val mockQuestions = listOf(
            Question(
                id = 1,
                questionText = "Where is Carthage located?",
                correctAnswer = "Tunis",
                imageResName = "carthage_img",
                category = Category.ROMAN_HERITAGE,
                difficulty = Difficulty.EASY
            )
        )

        `when`(repository.getQuestions(Category.ROMAN_HERITAGE, Difficulty.EASY))
            .thenReturn(mockQuestions)

        // Load questions into the ViewModel
        viewModel.loadQuestions(Category.ROMAN_HERITAGE, Difficulty.EASY)
        advanceUntilIdle()

        // FIXED: Changed to 'onAnswerSelected' to match the method name in QuizViewModel
        viewModel.onAnswerSelected("Tunis")
        advanceUntilIdle()

        // Verify state update
        assertEquals("Tunis", viewModel.quizState.value.selectedAnswer)
    }
}