package io.petros.movies.movies.list.item

import io.petros.movies.core.view.SharedElementMovie

interface MovieCallback {

    fun onClick(movie: SharedElementMovie)

    fun onErrorClick()

}
