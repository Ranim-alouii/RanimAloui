package com.example.ranimaloui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ranimaloui.data.Category
import com.example.ranimaloui.data.Difficulty
import com.example.ranimaloui.ui.screens.*
import com.example.ranimaloui.ui.theme.HeritageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "Stage: onCreate")
        enableEdgeToEdge()
        setContent {
            // Requirement: Wrap the NavHost in the system-aware custom HeritageTheme
            HeritageTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("main_menu") { MainMenuScreen(navController) }
                    composable("category") { CategorySelectionScreen(navController) }
                    composable("under_construction") { UnderConstructionScreen(navController) }
                    
                    composable("difficulty/{category}") { backStackEntry ->
                        val categoryStr = backStackEntry.arguments?.getString("category") ?: Category.ROMAN_HERITAGE.name
                        val category = Category.valueOf(categoryStr)
                        
                        if (category == Category.ROMAN_HERITAGE) {
                            DifficultySelectionScreen(navController, category)
                        } else {
                            UnderConstructionScreen(navController)
                        }
                    }
                    
                    composable("quiz/{category}/{difficulty}") { backStackEntry ->
                        val categoryStr = backStackEntry.arguments?.getString("category") ?: Category.ROMAN_HERITAGE.name
                        val category = Category.valueOf(categoryStr)
                        val difficulty = Difficulty.valueOf(backStackEntry.arguments?.getString("difficulty") ?: Difficulty.EASY.name)
                        
                        if (category == Category.ROMAN_HERITAGE) {
                            QuizScreen(navController, category, difficulty)
                        } else {
                            UnderConstructionScreen(navController)
                        }
                    }
                    
                    composable("results/{score}/{total}") { backStackEntry ->
                        val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
                        val total = backStackEntry.arguments?.getString("total")?.toInt() ?: 0
                        ResultsScreen(navController, score, total)
                    }
                }
            }
        }
    }

    // Requirement: Ensure all 7 lifecycle logs are present for evaluation
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "Stage: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "Stage: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "Stage: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "Stage: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "Stage: onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "Stage: onRestart")
    }
}
