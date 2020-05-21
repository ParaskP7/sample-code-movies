package io.petros.movies.domain.repository.movie

import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage

interface MoviesRepository {

    suspend fun loadMovies(year: Int?, month: Int?, page: Int?): Result<MoviesPage>

    suspend fun loadMovie(id: Int): Result<Movie>

}
