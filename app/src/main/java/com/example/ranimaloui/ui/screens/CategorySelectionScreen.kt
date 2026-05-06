package com.example.ranimaloui.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ranimaloui.R
import com.example.ranimaloui.data.Category
// Import the theme package to ensure all colors are visible
import com.example.ranimaloui.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectionScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.select_category),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                // Apply theme-aware colors to the TopAppBar
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Explore Tunisia's History",
                    // Use MaterialTheme.colorScheme.primary instead of hardcoded DeepMediterraneanBlue
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(Category.values()) { category ->
                Button(
                    onClick = {
                        if (category == Category.ROMAN_HERITAGE) {
                            navController.navigate("difficulty/${category.name}")
                        } else {
                            navController.navigate("under_construction")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp), // Slightly taller for better touch targets
                    // Theme-aware button colors
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        category.displayName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}