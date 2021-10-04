package io.petros.movies.data.repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import io.petros.movies.database.MoviesDatabase
import io.petros.movies.database.entity.toDate
import io.petros.movies.domain.model.asResult
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.network.MoviesService
import kotlinx.coroutines.flow.map

@Suppress("ForbiddenComment")
class MoviesRepositoryImpl(
    private val service: MoviesService,
    private val database: MoviesDatabase,
) : MoviesRepository {

    override suspend fun loadDate() = asResult {
        database.moviesDao().firstMovie().toDate()
    }

    // TODO: Figure out a way to test this function.
    override suspend fun loadMoviesStream(year: Int?, month: Int?) = pager(year, month).map { pagingData ->
        pagingData.map { it.toMovie() }
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun pager(year: Int?, month: Int?) = Pager(
        config = PagingConfig(
            pageSize = MoviesRemoteMediator.MOVIES_PAGE_SIZE,
        ),
        remoteMediator = MoviesRemoteMediator(
            service = service,
            database = database,
            year = year,
            month = month,
        ),
        pagingSourceFactory = { database.moviesDao().movies() }
    ).flow

    override suspend fun loadMovieStream(id: Int) = database.moviesDao().movie(id)
        .map { asResult { it.toMovie() } }

}
