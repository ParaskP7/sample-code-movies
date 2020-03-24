package io.petros.movies.presentation.feature.movies.listener

import io.petros.movies.presentation.feature.movies.view.SharedElementMovie

interface MovieCallback {

    fun onClick(movie: SharedElementMovie)

    fun onErrorClick()

}
