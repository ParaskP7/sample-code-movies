package io.petros.movies.domain.interactor.movie

import io.petros.movies.domain.interactor.UseCaseSingle
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.domain.reactive.rx.RxSchedulers
import io.petros.movies.domain.repository.movie.MoviesRepository
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    rxSchedulers: RxSchedulers
) : UseCaseSingle<MoviesResultPage, LoadMoviesUseCase.Params>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Params) = moviesRepository.loadMovies(params.year, params.month, params.page)

    data class Params constructor(val year: Int?, val month: Int?, val page: Int?) {

        companion object {

            fun with(year: Int?, month: Int?, page: Int?) = Params(year, month, page)

        }

    }

}
