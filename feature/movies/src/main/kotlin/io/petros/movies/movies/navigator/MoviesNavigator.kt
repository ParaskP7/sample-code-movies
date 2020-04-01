package io.petros.movies.movies.navigator

import io.petros.movies.movie_details.navigator.SharedElementMovie

interface MoviesNavigator {

    fun navigate(movie: SharedElementMovie)

    fun finish()

}
