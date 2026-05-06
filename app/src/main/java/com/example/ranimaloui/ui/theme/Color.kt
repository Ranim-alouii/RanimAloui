package com.example.ranimaloui.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Mediterranean Branding Colors
 * These constants fix the "Unresolved reference" errors in your screens.
 */
// Primary Brand Colors
val DeepMediterraneanBlue = Color(0xFF1E3A8A) // Fixed: Direct reference for existing screens
val Blue900 = DeepMediterraneanBlue          // Alias for Theme.kt consistency
val Blue400 = Color(0xFF60A5FA)               // Lighter blue for Dark Mode visibility

// Neutral & Background Tones
val Slate50 = Color(0xFFF8FAFC)               // Light Mode background
val Slate900 = Color(0xFF0F172A)              // Dark Mode background (Tunisian Night)
val DarkText = Color(0xFF1F2937)              // High contrast text for Light Mode

// Heritage Accent Tones
val WarmSand = Color(0xFFF5E6D3)              // Secondary color for cards/details
val AccentGreen = Color(0xFF10B981)            // Correct answer feedback
val AccentRed = Color(0xFFEF4444)              // Timer warning and error feedback

// Additional UI Tones for Depth
val Slate800 = Color(0xFF1E293B)              // Surface color for Dark Mode cards