@file:Suppress("MaximumLineLength")

package io.petros.movies.data.repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import io.petros.movies.database.MoviesDatabase
import io.petros.movies.database.entity.MovieEntity
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.network.MoviesService
import timber.log.Timber

@Suppress("CommentOverPrivateFunction")
@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator(
    private val service: MoviesService,
    private val database: MoviesDatabase,
    private val year: Int?,
    private val month: Int?,
) : RemoteMediator<Int, MovieEntity>() {

    @Suppress("ReturnCount", "TooGenericExceptionCaught")
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> getNextPageForCurrentItem(state)?.let { nextPage ->
                Timber.w("Next page is found for current item, ignore pagination. [Next Page: $nextPage]")
                return MediatorResult.Success(endOfPaginationReached = false)
            } ?: MOVIES_STARTING_PAGE
            LoadType.APPEND -> getNextPageForLastItem(state)
            LoadType.PREPEND -> getPreviousPageForFirstItem(state) ?: run {
                Timber.i("No previous page is found for first item, end of pagination reached.")
                return MediatorResult.Success(endOfPaginationReached = true)
            }
        }

        return try {
            val movies = service.loadMovies(year, month, page).items
            if (movies.isNotEmpty()) {
                updateDatabase(loadType, page, movies)
                MediatorResult.Success(endOfPaginationReached = false)
            } else {
                Timber.i("No more items are found for this page, end of pagination reached. [Page: $page]")
                MediatorResult.Success(endOfPaginationReached = true)
            }
        } catch (e: Exception) {
            Timber.e(e, "An error occurred while page was loading. [Year: $year, Month: $month, Page: $page]")
            MediatorResult.Error(e)
        }
    }

    /**
     * Get the item closest to the anchor position. From that item, get the next page.
     */
    private fun getNextPageForCurrentItem(state: PagingState<Int, MovieEntity>) =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { movieEntity ->
                Timber.v("Get next page for a refresh load. [Movie Entity: $movieEntity]")
                movieEntity.nextPage
            }
        }

    /**
     * Get the last page that was retrieved, that contained items. From that last page, get the last item. From that
     * item, get the next page.
     */
    private fun getNextPageForLastItem(state: PagingState<Int, MovieEntity>) =
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { movieEntity ->
            Timber.v("Get next page for an append load. [Movie Entity: $movieEntity]")
            movieEntity.nextPage
        }

    /**
     * Get the first page that was retrieved, that contained items. From that first page, get the first item. From that
     * item, get the previous page.
     */
    private fun getPreviousPageForFirstItem(state: PagingState<Int, MovieEntity>) =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { movieEntity ->
            Timber.v("Get previous page for a prepend load. [Movie Entity: $movieEntity]")
            movieEntity.prevPage
        }

    private suspend fun updateDatabase(
        loadType: LoadType,
        page: Int?,
        movies: List<Movie>,
    ) = database.withTransaction {
        checkAndClearMovies(loadType)
        insertMovies(page, movies)
    }

    /**
     * When the load type is refresh, which is either when the app first loads or when a user manually triggers a
     * reload, then clear the available list of movies from the database to start over.
     */
    private suspend fun checkAndClearMovies(loadType: LoadType) {
        if (loadType == LoadType.REFRESH) {
            Timber.i("This is a refresh load, will clear all movies from the database.")
            database.moviesDao().clear()
        } else {
            Timber.v("This is a normal load, will not clear any movies from the database. [Load Type: $loadType]")
        }
    }

    /**
     * Insert the list of movies, but first:
     * - Enhance each movie with its previous and next page for pagination purposes, and then
     * - Enhance each movie with its year and month for offline purposes.
     */
    private suspend fun insertMovies(page: Int?, movies: List<Movie>) {
        database.moviesDao().insert(
            movies.map { movie ->
                MovieEntity.from(
                    prevPage = if (page == MOVIES_STARTING_PAGE) null else page?.minus(1),
                    nextPage = page?.plus(1),
                    year = year,
                    month = month,
                    movie = movie,
                )
            }
        )
    }

    companion object {

        // TMDb page API is 1 based: https://developers.themoviedb.org/3/discover/movie-discover
        const val MOVIES_STARTING_PAGE = 1

        // TMDb page size is 20 by default and cannot change.
        const val MOVIES_PAGE_SIZE = 20

    }

}
