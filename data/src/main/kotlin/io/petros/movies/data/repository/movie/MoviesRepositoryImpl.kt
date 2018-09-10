package io.petros.movies.data.repository.movie

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor() : MoviesRepository {

    override fun loadMovies(): Single<MoviesResultPage> {
        return Single.just(MoviesResultPage(listOf(Movie("Movie.1"), Movie("Movie.2"), Movie("Movie.3"))))
    }

}
