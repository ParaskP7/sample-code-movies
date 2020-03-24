package io.petros.movies.network

import io.petros.movies.domain.model.movie.MoviesResultPage

interface WebService {

    suspend fun loadMovies(year: Int?, month: Int?, page: Int?): MoviesResultPage

}
