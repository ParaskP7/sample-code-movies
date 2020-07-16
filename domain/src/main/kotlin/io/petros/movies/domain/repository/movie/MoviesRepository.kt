package io.petros.movies.domain.repository.movie

import androidx.paging.PagingData
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun loadMovies(year: Int?, month: Int?, page: Int?): Result<MoviesPage>

    suspend fun loadMoviesStream(year: Int?, month: Int?): Flow<PagingData<Movie>>

    suspend fun loadMovie(id: Int): Result<Movie>

}
