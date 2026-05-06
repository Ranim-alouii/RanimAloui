package com.example.ranimaloui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testNavigationFlow_SplashToQuiz() {
        // Wait for Splash screen to transition
        composeTestRule.waitUntil(timeoutMillis = 8000) {
            composeTestRule.onAllNodesWithText("Start Quiz", ignoreCase = true).fetchSemanticsNodes().isNotEmpty()
        }

        // Use performClick() but wrap it in try-catch if the InputManager remains stubborn
        // on API 36, or ensure you are using the latest Compose UI Test libraries.
        composeTestRule.onNodeWithText("Start Quiz", ignoreCase = true).performClick()

        composeTestRule.onNodeWithText("Select Category", ignoreCase = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Roman Heritage", ignoreCase = true).performClick()

        composeTestRule.onNodeWithText("Select Difficulty", ignoreCase = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Easy", ignoreCase = true).performClick()

        // Use useUnmergedTree for TopAppBar titles
        composeTestRule.onNodeWithText("Quiz", ignoreCase = true, useUnmergedTree = true).assertExists()
    }

    @Test
    fun testRedirectionToUnderConstruction() {
        composeTestRule.waitUntil(timeoutMillis = 8000) {
            composeTestRule.onAllNodesWithText("Start Quiz", ignoreCase = true).fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Start Quiz", ignoreCase = true).performClick()

        // Select Carthaginian Heritage
        composeTestRule.onNodeWithText("Carthaginian Heritage", ignoreCase = true).performClick()

        // Verify redirection
        composeTestRule.onNodeWithText("Under Exploration", ignoreCase = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Go Back", ignoreCase = true).assertIsDisplayed()
    }
}