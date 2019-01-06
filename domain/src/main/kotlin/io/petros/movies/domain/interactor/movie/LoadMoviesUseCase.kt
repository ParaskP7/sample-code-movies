package io.petros.movies.domain.interactor.movie

import io.petros.movies.domain.interactor.BaseUseCase
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.domain.repository.movie.MoviesRepository
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<MoviesResultPage, LoadMoviesUseCase.Params, LoadMoviesUseCase.Error>() {

    override suspend fun buildUseCase(params: Params) = moviesRepository.loadMovies(params.year, params.month, params.page)

    override suspend fun buildError(e: Exception) = Error(e)

    data class Params(val year: Int?, val month: Int?, val page: Int?)

    class Error(cause: Throwable) : Throwable(cause.message, cause)

}
