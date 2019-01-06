package io.petros.movies.domain.repository.movie

import io.petros.movies.domain.model.movie.MoviesResultPage

interface MoviesRepository {

    suspend fun loadMovies(year: Int?, month: Int?, page: Int?): MoviesResultPage

}
