package io.petros.movies.data.network

import io.petros.movies.domain.model.movie.MoviesResultPage

interface WebService {

    suspend fun loadMovies(year: Int?, month: Int?, page: Int?): MoviesResultPage

}
