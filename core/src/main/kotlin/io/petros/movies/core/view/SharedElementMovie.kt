package io.petros.movies.core.view

import android.view.View
import io.petros.movies.domain.model.movie.Movie

data class SharedElementMovie(
    val movie: Movie,
    val sharedElement: View
)
