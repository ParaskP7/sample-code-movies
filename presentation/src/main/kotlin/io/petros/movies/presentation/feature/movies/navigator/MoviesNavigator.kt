package io.petros.movies.presentation.feature.movies.navigator

import io.petros.movies.presentation.feature.movies.view.SharedElementMovie

interface MoviesNavigator {

    fun navigate(movie: SharedElementMovie)

    fun finish()

}
