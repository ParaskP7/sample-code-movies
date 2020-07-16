package io.petros.movies.domain.interactor.movie

import io.petros.movies.domain.repository.movie.MoviesRepository

class LoadMoviesUseCase(
    private val moviesRepository: MoviesRepository,
) {

    suspend fun execute(params: Params) = moviesRepository.loadMovies(params.year, params.month, params.page)

    suspend operator fun invoke(params: Params) = moviesRepository.loadMoviesStream(params.year, params.month)

    data class Params(val year: Int?, val month: Int?, val page: Int?)

}
