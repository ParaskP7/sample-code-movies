package io.petros.movies.presentation.feature.movie.navigator

import io.petros.movies.domain.model.movie.Movie

interface MovieDetailsLauncher {

    fun launch(movie: Movie)

}
