package io.petros.movies.core_compose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
@Suppress("unused")
fun MoviesTheme(
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
