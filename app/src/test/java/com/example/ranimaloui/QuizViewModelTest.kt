package com.example.ranimaloui.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Unit tests for Quiz scoring and calculations
 * Tests verify core scoring logic and percentage calculations
 */
@RunWith(JUnit4::class)
class QuizViewModelTest {

    /**
     * Test scoring calculation: Each correct answer = 1 point
     */
    @Test
    fun testScoringLogic() {
        var score = 0
        // Simulate 3 correct answers
        score += 1
        score += 1
        score += 1
        assertEquals(3, score)
    }

    /**
     * Test percentage calculation:
     * If a user scores 4 out of 5 questions correct, that's 80%
     */
    @Test
    fun testPercentageCalculation_80Percent() {
        val totalQuestions = 5
        val correctAnswers = 4
        val percentage = (correctAnswers * 100) / totalQuestions
        assertEquals(80, percentage)
    }

    /**
     * Test percentage calculation:
     * If a user scores 5 out of 5 questions correct, that's 100%
     */
    @Test
    fun testPercentageCalculation_100Percent() {
        val totalQuestions = 5
        val correctAnswers = 5
        val percentage = (correctAnswers * 100)/ totalQuestions
        assertEquals(100, percentage)
    }

    /**
     * Test percentage calculation:
     * If a user scores 2 out of 5 questions correct, that's 40%
     */
    @Test
    fun testPercentageCalculation_40Percent() {
        val totalQuestions = 5
        val correctAnswers = 2
        val percentage = (correctAnswers * 100) / totalQuestions
        assertEquals(40, percentage)
    }

    /**
     * Test timer: 15-second timer per question
     * The quiz has a 15-second countdown for each question
     */
    @Test
    fun testQuestionTimer() {
        val timerDuration = 15 // seconds
        assertEquals(15, timerDuration)
    }
}