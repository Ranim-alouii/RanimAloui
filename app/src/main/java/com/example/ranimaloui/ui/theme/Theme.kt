package com.example.ranimaloui.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Mediterranean Dark Palette - "Tunisian Night"
 */
private val DarkColorScheme = darkColorScheme(
    primary = Blue400, // Lighter blue for accessibility in dark mode
    onPrimary = Color.Black,
    background = Slate900,
    surface = Color(0xFF1E293B),
    onBackground = Color.White,
    onSurface = Color.White,
    secondary = WarmSand,
    tertiary = AccentGreen,
    error = AccentRed
)

/**
 * Mediterranean Light Palette - "Sun-washed Heritage"
 */
private val LightColorScheme = lightColorScheme(
    primary = Blue900, // Deep Mediterranean Blue
    onPrimary = Color.White,
    background = Slate50,
    surface = Color.White,
    onBackground = DarkText,
    onSurface = DarkText,
    secondary = WarmSand,
    tertiary = AccentGreen,
    error = AccentRed
)

@Composable
fun HeritageTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // We set dynamicColor to false to keep the specific Tunisia Heritage brand colors
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // This block ensures the Status Bar color matches the theme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}