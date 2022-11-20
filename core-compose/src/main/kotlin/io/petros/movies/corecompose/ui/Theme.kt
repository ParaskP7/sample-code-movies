package io.petros.movies.corecompose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColor.colors else LightColor.colors,
        typography = if (darkTheme) DarkTypography.typography else LightTypography.typography,
        shapes = shapes,
        content = content,
    )
}
