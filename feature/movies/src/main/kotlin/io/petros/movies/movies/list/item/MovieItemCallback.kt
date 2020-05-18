package io.petros.movies.movies.list.item

import io.petros.movies.domain.model.movie.Movie

interface MovieItemCallback {

    fun onClick(movie: Movie)

}
