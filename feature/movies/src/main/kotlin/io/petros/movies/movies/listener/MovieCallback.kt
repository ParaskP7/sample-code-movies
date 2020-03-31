package io.petros.movies.movies.listener

import io.petros.movies.core.view.SharedElementMovie

interface MovieCallback {

    fun onClick(movie: SharedElementMovie)

    fun onErrorClick()

}
