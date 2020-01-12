package io.petros.movies.domain.interactor.movie

import io.petros.movies.domain.repository.movie.MoviesRepository

class LoadMoviesUseCase constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend fun execute(params: Params) = moviesRepository.loadMovies(params.year, params.month, params.page)

    data class Params(val year: Int?, val month: Int?, val page: Int?)

    class Error(cause: Throwable) : Throwable(cause.message, cause)

}
