package io.petros.movies.movies.list.item

import io.petros.movies.movie_details.navigator.SharedElementMovie

interface MovieItemCallback {

    fun onClick(movie: SharedElementMovie)

}
