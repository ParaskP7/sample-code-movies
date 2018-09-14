package io.petros.movies.data.network

import io.petros.movies.domain.model.movie.MoviesResultPage
import io.reactivex.Single

interface WebService {

    fun loadMovies(year: Int?, page: Int?): Single<MoviesResultPage>

}
