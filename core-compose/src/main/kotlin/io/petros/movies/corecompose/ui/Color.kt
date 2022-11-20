package io.petros.movies.corecompose.ui

import android.annotation.SuppressLint
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object LightColor {

    @SuppressLint("ConflictingOnColor")
    val colors = lightColors(
        primary = green700,
        primaryVariant = green800,
        onPrimary = Color.Black,
        secondary = pinkA200,
        secondaryVariant = pinkA700,
        onSecondary = Color.White,
        background = Color.White,
        onBackground = green700,
        surface = Color.White,
        onSurface = Color.Black,
        error = lightError,
        onError = Color.White,
    )

    /* APP */
    val containerBackground = colors.background

}

object DarkColor {

    @SuppressLint("ConflictingOnColor")
    val colors = darkColors(
        primary = greenA700,
        primaryVariant = green800,
        onPrimary = Color.White,
        secondary = pinkA200,
        onSecondary = Color.Black,
        background = Color.Black,
        onBackground = green800,
        surface = Color.Black,
        onSurface = Color.White,
        error = darkError,
        onError = Color.Black,
    )

    /* APP */
    val containerBackground = colors.primaryVariant

}
