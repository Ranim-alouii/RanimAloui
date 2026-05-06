package com.example.ranimaloui.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ranimaloui.R
import com.example.ranimaloui.data.Category
import com.example.ranimaloui.data.Difficulty
import com.example.ranimaloui.viewmodel.QuizViewModel
import com.example.ranimaloui.data.HeritageRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavController, category: Category, difficulty: Difficulty) {
    val context = LocalContext.current
    val repository = remember { HeritageRepository(context) }
    val viewModel: QuizViewModel = viewModel { QuizViewModel(repository) }
    val quizState by viewModel.quizState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadQuestions(category, difficulty)
    }

    if (quizState.questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        return
    }

    val currentQuestion = quizState.questions[quizState.currentQuestionIndex]
    val progress = (quizState.currentQuestionIndex + 1).toFloat() / quizState.questions.size.toFloat()

    val options = remember(currentQuestion) {
        listOf(
            "Carthage site",
            "Dougga ruins",
            "El Jem Amphitheater",
            currentQuestion.correctAnswer
        ).shuffled()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.quiz_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val horizontalPadding = if (maxWidth > 600.dp) 64.dp else 16.dp

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = horizontalPadding, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeCap = StrokeCap.Round
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Question ${quizState.currentQuestionIndex + 1}/${quizState.questions.size}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        val imageResId = context.resources.getIdentifier(
                            currentQuestion.imageResName, "drawable", context.packageName
                        )
                        if (imageResId != 0) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                item {
                    Text(
                        currentQuestion.questionText,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                item {
                    Text(
                        "Time: ${quizState.timeLeft}s",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (quizState.timeLeft < 5) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(options) { option ->
                    val isSelected = quizState.selectedAnswer == option
                    val isCorrectAnswer = option == currentQuestion.correctAnswer
                    val isAnswered = quizState.isAnswered

                    val containerColor = when {
                        !isAnswered -> MaterialTheme.colorScheme.surface
                        isCorrectAnswer -> MaterialTheme.colorScheme.tertiary
                        isSelected && !isCorrectAnswer -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.surface
                    }

                    val contentColor = when {
                        !isAnswered -> MaterialTheme.colorScheme.primary
                        isCorrectAnswer || (isSelected && !isCorrectAnswer) -> MaterialTheme.colorScheme.onPrimary
                        else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    }

                    val borderColor = when {
                        isAnswered && isCorrectAnswer -> MaterialTheme.colorScheme.tertiary
                        isAnswered && isSelected && !isCorrectAnswer -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.primary
                    }

                    OutlinedButton(
                        onClick = { if (!isAnswered) viewModel.onAnswerSelected(option) },
                        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                        enabled = !isAnswered || isSelected || isCorrectAnswer,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = containerColor,
                            contentColor = contentColor,
                            disabledContainerColor = containerColor,
                            disabledContentColor = contentColor
                        ),
                        border = BorderStroke(2.dp, borderColor),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            option,
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 17.sp,
                            fontWeight = if (isSelected || (isAnswered && isCorrectAnswer)) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }

                item {
                    if (quizState.isAnswered) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                if (quizState.currentQuestionIndex + 1 < quizState.questions.size) {
                                    viewModel.nextQuestion()
                                } else {
                                    navController.navigate("results/${quizState.score}/${quizState.questions.size}")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                        ) {
                            Text(
                                if (quizState.currentQuestionIndex + 1 >= quizState.questions.size) "See Results" else "Next Question",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
