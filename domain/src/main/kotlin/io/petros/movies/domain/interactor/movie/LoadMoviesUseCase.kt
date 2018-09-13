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
) : UseCaseSingle<MoviesResultPage, LoadMoviesUseCase.Params>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Params): Single<MoviesResultPage> {
        return moviesRepository.loadMovies(params.year)
    }

    data class Params constructor(val year: Int?) {

        companion object {

            fun with(year: Int?): Params {
                return Params(year)
            }

        }

    }

}
