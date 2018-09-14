package io.petros.movies.domain.repository.movie

import io.petros.movies.domain.model.movie.MoviesResultPage
import io.reactivex.Single

interface MoviesRepository {

    fun loadMovies(year: Int?, page: Int?): Single<MoviesResultPage>

}
