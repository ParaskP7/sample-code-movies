package io.petros.movies.presentation.feature.movie.navigator

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.movies.view.SharedElementMovie

interface MovieDetailsLauncher {

    fun launch(movie: Movie)

    fun launch(movie: SharedElementMovie)

}
