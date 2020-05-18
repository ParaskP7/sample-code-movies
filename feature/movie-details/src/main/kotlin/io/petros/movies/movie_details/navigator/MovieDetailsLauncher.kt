package io.petros.movies.movie_details.navigator

import io.petros.movies.domain.model.movie.Movie

interface MovieDetailsLauncher {

    fun launch(movie: Movie)

    fun finish()

}
