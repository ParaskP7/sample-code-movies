package io.petros.movies.movies.navigator

import io.petros.movies.core.view.SharedElementMovie

interface MoviesNavigator {

    fun navigate(movie: SharedElementMovie)

    fun finish()

}
