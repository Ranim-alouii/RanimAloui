package com.example.ranimaloui.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Blue400,
    onPrimary = Color.Black,
    background = DarkBackground,
    surface = SurfaceDark,
    onBackground = Color.White,
    onSurface = Color.White,
    secondary = WarmSand,
    tertiary = AccentGreen,
    error = AccentRed
)

private val LightColorScheme = lightColorScheme(
    primary = DeepMediterraneanBlue,
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
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

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
