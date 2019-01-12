package io.petros.movies.data.repository.movie

import io.petros.movies.data.network.WebService
import io.petros.movies.domain.repository.movie.MoviesRepository

class MoviesRepositoryImpl constructor(
    private val webService: WebService
) : MoviesRepository {

    override suspend fun loadMovies(year: Int?, month: Int?, page: Int?) = webService.loadMovies(year, month, page)

}
