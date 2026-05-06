package com.example.ranimaloui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Robust Navigation Tests for Tunisia Heritage Quest.
 * Uses TestTags and performTouchInput to ensure compatibility with API 35/36
 * and avoid legacy InputManager exceptions.
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val splashToMenuTimeout = 12000L
    private val interactionTimeout = 5000L

    @Test
    fun testNavigationFlow_SplashToQuiz() {
        // 1. Wait for Main Menu to load after Splash
        composeTestRule.waitUntil(splashToMenuTimeout) {
            composeTestRule.onAllNodesWithTag("start_quiz_button").fetchSemanticsNodes().isNotEmpty()
        }
        
        // Use testTag for reliable selection and performTouchInput to bypass legacy injection
        composeTestRule.onNodeWithTag("start_quiz_button", useUnmergedTree = true)
            .assertIsDisplayed()
            .performTouchInput { click() }

        // 2. Wait for Category Screen
        composeTestRule.waitUntil(interactionTimeout) {
            composeTestRule.onAllNodesWithTag("category_list").fetchSemanticsNodes().isNotEmpty()
        }
        
        // Select 'Roman Heritage' via its unique test tag
        composeTestRule.onNodeWithTag("category_button_ROMAN_HERITAGE", useUnmergedTree = true)
            .assertIsDisplayed()
            .performTouchInput { click() }

        // 3. Wait for Difficulty Screen
        composeTestRule.waitUntil(interactionTimeout) {
            composeTestRule.onAllNodesWithTag("difficulty_button_EASY").fetchSemanticsNodes().isNotEmpty()
        }
        
        composeTestRule.onNodeWithTag("difficulty_button_EASY", useUnmergedTree = true)
            .assertIsDisplayed()
            .performTouchInput { click() }

        // 4. Verify Quiz Screen is active
        composeTestRule.waitUntil(interactionTimeout) {
            composeTestRule.onAllNodesWithText("Quiz").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Quiz").assertExists()
    }

    @Test
    fun testRedirectionToUnderConstruction() {
        // 1. Wait for Main Menu
        composeTestRule.waitUntil(splashToMenuTimeout) {
            composeTestRule.onAllNodesWithTag("start_quiz_button").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("start_quiz_button").performTouchInput { click() }

        // 2. Select an unfinished category (e.g., Carthaginian)
        composeTestRule.waitUntil(interactionTimeout) {
            composeTestRule.onAllNodesWithTag("category_button_CARTHAGINIAN_HERITAGE").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("category_button_CARTHAGINIAN_HERITAGE").performTouchInput { click() }

        // 3. Verify redirection to Under Construction
        composeTestRule.waitUntil(interactionTimeout) {
            composeTestRule.onAllNodesWithText("Under Exploration").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Under Exploration").assertExists()
        composeTestRule.onNodeWithText("Go Back").assertExists()
    }
}
