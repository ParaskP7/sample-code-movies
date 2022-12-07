package io.petros.movies.domain.interactor.movie

import io.petros.movies.domain.repository.movie.MoviesRepository

class LoadMovieUseCase(
    private val moviesRepository: MoviesRepository,
) {

    operator fun invoke(params: Params) = moviesRepository.loadMovieStream(params.id)

    data class Params(val id: Int)

}
