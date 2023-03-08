package me.brisson.newsfeed.presentation.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = primaryDark,
    secondary = secondaryDark,
    background = surfaceDark,
    onBackground = onSurfaceDark,
    surface = surfaceDark,
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = primaryLight,
    secondary = secondaryLight,
    background = surfaceLight,
    onBackground = onSurfaceLight,
    surface = surfaceLight,
)

@Composable
fun NewsFeedTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = if (darkTheme) statusBarDark else statusBarLight
    )

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}