package io.petros.movies.movies.list.item

import io.petros.movies.core.view.SharedElementMovie

interface MovieItemCallback {

    fun onClick(movie: SharedElementMovie)

    fun onErrorClick()

}
