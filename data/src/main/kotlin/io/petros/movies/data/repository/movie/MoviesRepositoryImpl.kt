package io.petros.movies.data.repository.movie

import io.petros.movies.data.network.WebService
import io.petros.movies.domain.repository.movie.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val webService: WebService
) : MoviesRepository {

    override fun loadMovies(year: Int?, month: Int?, page: Int?) = webService.loadMovies(year, month, page)

}
