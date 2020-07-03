package io.petros.movies.data.repository.movie

import io.petros.movies.domain.model.asResult
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.network.WebService

class MoviesRepositoryImpl(
    private val webService: WebService,
) : MoviesRepository {

    override suspend fun loadMovies(year: Int?, month: Int?, page: Int?) = asResult {
        webService.loadMovies(year, month, page)
    }

    override suspend fun loadMovie(id: Int) = asResult {
        webService.loadMovie(id)
    }

}
