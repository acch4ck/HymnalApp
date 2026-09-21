package com.faith.hymnal.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF3F51B5),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDEE0FF),
    onPrimaryContainer = Color(0xFF001159),
    secondary = Color(0xFF5A5D72),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE0E1F9),
    onSecondaryContainer = Color(0xFF171A2C),
    tertiary = Color(0xFF78536B),
    background = Color(0xFFFEFBFF),
    surface = Color(0xFFFEFBFF),
    surfaceVariant = Color(0xFFE2E1EC),
    onBackground = Color(0xFF1B1B1F),
    onSurface = Color(0xFF1B1B1F),
    onSurfaceVariant = Color(0xFF45464F)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFB9C3FF),
    onPrimary = Color(0xFF00208F),
    primaryContainer = Color(0xFF24379B),
    onPrimaryContainer = Color(0xFFDEE0FF),
    secondary = Color(0xFFC3C5DD),
    onSecondary = Color(0xFF2C2F42),
    secondaryContainer = Color(0xFF434659),
    onSecondaryContainer = Color(0xFFE0E1F9),
    tertiary = Color(0xFFE8B9D5),
    background = Color(0xFF1B1B1F),
    surface = Color(0xFF1B1B1F),
    surfaceVariant = Color(0xFF45464F),
    onBackground = Color(0xFFE3E1E6),
    onSurface = Color(0xFFE3E1E6),
    onSurfaceVariant = Color(0xFFC6C5D0)
)

@Composable
fun HymnalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography(),
        content = content
    )
}
