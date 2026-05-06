package com.example.ranimaloui.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ranimaloui.R
import com.example.ranimaloui.data.Category
import com.example.ranimaloui.data.Difficulty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultySelectionScreen(navController: NavController, category: Category) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.select_difficulty)) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Difficulty.values().forEach { difficulty ->
                Button(
                    onClick = { navController.navigate("quiz/${category.name}/${difficulty.name}") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(
                        when (difficulty) {
                            Difficulty.EASY -> R.string.easy
                            Difficulty.MEDIUM -> R.string.medium
                            Difficulty.HARD -> R.string.hard
                        }
                    ))
                }
            }
        }
    }
}
