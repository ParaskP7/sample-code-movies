package io.petros.movies.movies.navigator

import io.petros.movies.domain.model.movie.Movie

interface MoviesNavigator {

    fun navigate(movie: Movie)

    fun finish()

}
