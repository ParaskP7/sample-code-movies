package io.petros.movies.presentation.feature.movies.navigator

import io.petros.movies.domain.model.movie.Movie

interface MoviesNavigator {

    fun navigate(movie: Movie)

}
