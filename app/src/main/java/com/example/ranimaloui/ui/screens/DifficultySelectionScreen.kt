package com.example.ranimaloui.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ranimaloui.R
import com.example.ranimaloui.data.Category
import com.example.ranimaloui.data.Difficulty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultySelectionScreen(navController: NavController, category: Category) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.select_difficulty)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = category.displayName,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily.Serif,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            
            Difficulty.values().forEach { difficulty ->
                Button(
                    onClick = { navController.navigate("quiz/${category.name}/${difficulty.name}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("difficulty_button_${difficulty.name}"), // Added for reliable testing
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        stringResource(
                            when (difficulty) {
                                Difficulty.EASY -> R.string.easy
                                Difficulty.MEDIUM -> R.string.medium
                                Difficulty.HARD -> R.string.hard
                            }
                        ),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
