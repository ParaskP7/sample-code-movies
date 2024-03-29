package io.petros.movies.domain.repository.movie

import androidx.paging.PagingData
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun loadDate(): Result<Pair<Int?, Int?>>

    fun loadMoviesStream(year: Int?, month: Int?): Flow<PagingData<Movie>>

    fun loadMovieStream(id: Int): Flow<Result<Movie>>

}
