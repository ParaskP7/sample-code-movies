package io.petros.movies.presentation.feature.movies.listener

import io.petros.movies.domain.model.movie.Movie

interface MovieCallback {

    fun onClick(movie: Movie)

}
