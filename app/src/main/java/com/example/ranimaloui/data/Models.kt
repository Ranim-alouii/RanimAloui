package com.example.ranimaloui.data

enum class Category(val displayName: String) {
    ROMAN_HERITAGE("Roman Heritage"),
    CARTHAGINIAN_HERITAGE("Carthaginian Heritage"),
    ISLAMIC_HERITAGE("Islamic Heritage"),
    OTTOMAN_HERITAGE("Ottoman Heritage"),
    FRENCH_COLONIAL_HERITAGE("French Colonial Heritage"),
    MODERN_TUNISIA("Modern Tunisia")
}

enum class Difficulty {
    EASY, MEDIUM, HARD
}

data class Question(
    val id: Int,
    val category: Category,
    val difficulty: Difficulty,
    val questionText: String,
    val correctAnswer: String,
    val imageResName: String
)
