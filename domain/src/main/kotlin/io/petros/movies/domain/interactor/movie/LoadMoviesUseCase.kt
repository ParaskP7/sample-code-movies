package io.petros.movies.domain.interactor.movie

import io.petros.movies.domain.interactor.UseCaseSingle
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.domain.reactive.rx.RxSchedulers
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    rxSchedulers: RxSchedulers
) : UseCaseSingle<MoviesResultPage, Unit>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Unit): Single<MoviesResultPage> {
        return moviesRepository.loadMovies()
    }

}
