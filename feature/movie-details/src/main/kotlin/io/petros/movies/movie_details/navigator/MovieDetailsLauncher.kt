package io.petros.movies.movie_details.navigator

import io.petros.movies.core.view.SharedElementMovie
import io.petros.movies.domain.model.movie.Movie

interface MovieDetailsLauncher {

    fun launch(movie: Movie)

    fun launch(movie: SharedElementMovie)

    fun finish()

}
