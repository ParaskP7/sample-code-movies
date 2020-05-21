package io.petros.movies.network

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage

interface WebService {

    suspend fun loadMovies(year: Int?, month: Int?, page: Int?): MoviesPage

    suspend fun loadMovie(id: Int): Movie

}
