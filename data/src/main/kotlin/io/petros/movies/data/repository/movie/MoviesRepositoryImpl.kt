package io.petros.movies.data.repository.movie

import io.petros.movies.data.network.WebService
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val webService: WebService
) : MoviesRepository {

    override fun loadMovies(year: Int?): Single<MoviesResultPage> {
        return webService.loadMovies(year)
    }

}
