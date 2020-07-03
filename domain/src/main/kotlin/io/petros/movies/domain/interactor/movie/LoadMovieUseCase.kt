package io.petros.movies.domain.interactor.movie

import io.petros.movies.domain.repository.movie.MoviesRepository

class LoadMovieUseCase(
    private val moviesRepository: MoviesRepository,
) {

    suspend fun execute(params: Params) = moviesRepository.loadMovie(params.id)

    data class Params(val id: Int)

}
