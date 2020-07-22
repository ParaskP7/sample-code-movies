package io.petros.movies.domain.interactor.movie

import io.petros.movies.domain.repository.movie.MoviesRepository

class LoadDateUseCase(
    private val moviesRepository: MoviesRepository,
) {

    suspend operator fun invoke() = moviesRepository.loadDate()

}
