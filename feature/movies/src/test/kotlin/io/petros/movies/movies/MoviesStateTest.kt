package io.petros.movies.movies

import androidx.paging.LoadType
import androidx.paging.PagingData
import io.mockk.mockk
import io.petros.movies.domain.model.movie.Movie
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

class MoviesStateTest {

    companion object {

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

    @Test
    fun `when init is triggered, then the initial state is the expected one`() {
        val result = MoviesReducer.init()

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    status = MoviesStatus.Init,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `given an idle action, when reduce is triggered, then the new state is the expected one`() {
        val previousState = MoviesState(
            year = null,
            month = null,
            status = MoviesStatus.Init,
            movies = PagingData.empty(),
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Idle(MOVIE_YEAR, MOVIE_MONTH))

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Idle,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `given a load action, when reduce is triggered, then the new state is the expected one`() {
        val previousState = MoviesState(
            year = MOVIE_YEAR,
            month = MOVIE_MONTH,
            status = MoviesStatus.Idle,
            movies = PagingData.empty(),
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Load(MOVIE_YEAR, MOVIE_MONTH))

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loading,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `given a reload action, when reduce is triggered, then the new state is the expected one`() {
        val previousState = MoviesState(
            year = MOVIE_YEAR,
            month = MOVIE_MONTH,
            status = MoviesStatus.Loaded,
            movies = mockk(),
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Reload(MOVIE_YEAR, MOVIE_MONTH))

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loaded,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `given a success action, when reduce is triggered, then the new state is the expected one`() {
        val movies = PagingData.empty<Movie>()
        val previousState = MoviesState(
            year = MOVIE_YEAR,
            month = MOVIE_MONTH,
            status = MoviesStatus.Loading,
            movies = mockk(),
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Success(movies))

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loaded,
                    movies = movies,
                )
            )
        }
    }

    @Test
    fun `given an error action, when reduce is triggered, then the new state is the expected one`() {
        val movies = PagingData.empty<Movie>()
        val previousState = MoviesState(
            year = MOVIE_YEAR,
            month = MOVIE_MONTH,
            status = MoviesStatus.Loading,
            movies = movies,
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Error(LoadType.APPEND))

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loaded,
                    movies = movies,
                )
            )
        }
    }

    @Test
    fun `given a refresh error action, when once is triggered, then the side effect is the expected one`() {
        val result = MoviesReducer.once(MoviesAction.Error(LoadType.REFRESH))

        expect { that(result).isEqualTo(MoviesSideEffect.MoviesRefreshError) }
    }

    @Test
    fun `given an append error action, when once is triggered, then the side effect is the expected one`() {
        val result = MoviesReducer.once(MoviesAction.Error(LoadType.APPEND))

        expect { that(result).isEqualTo(MoviesSideEffect.MoviesAppendError) }
    }

    @Test
    fun `given a prepend error action, when once is triggered, then the side effect is the expected one`() {
        val result = MoviesReducer.once(MoviesAction.Error(LoadType.PREPEND))

        expect { that(result).isEqualTo(MoviesSideEffect.MoviesPrependError) }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given an unexpected action, when once is triggered, then throw illegal argument exception`() {
        MoviesReducer.once(MoviesAction.Load(null, null))
    }

}
