package com.headbook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = Blue,
    background = OffWhite,
    surface = LightGray,
    onPrimary = Color.White,
    onBackground = DarkGray,
    onSurface = Gray,
)

@Composable
fun HeadbookTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}