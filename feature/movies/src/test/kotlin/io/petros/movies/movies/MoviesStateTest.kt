package io.petros.movies.movies

import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.test.domain.movie
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

class MoviesStateTest {

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

    private val firstPageItems = listOf(movie(id = 1), movie(id = 2), movie(id = 3))

    @Test
    fun `when init is triggered, then the initial state is the expected one`() {
        val result = MoviesReducer.init()

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    status = MoviesStatus.Init,
                    movies = PaginationData()
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
            movies = PaginationData()
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Idle)

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    status = MoviesStatus.Idle,
                    movies = PaginationData()
                )
            )
        }
    }

    @Test
    fun `given a load action, when reduce is triggered, then the new state is the expected one`() {
        val previousState = MoviesState(
            year = null,
            month = null,
            status = MoviesStatus.Idle,
            movies = PaginationData()
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Load(MOVIE_YEAR, MOVIE_MONTH))

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loading,
                    movies = PaginationData()
                )
            )
        }
    }

    @Test
    fun `given a reload action, when reduce is triggered, then the new state is the expected one`() {
        val paginationData = PaginationData<Movie>()
        val moviesPage = MoviesPage(SECOND_PAGE, firstPageItems)
        val previousState = MoviesState(
            year = MOVIE_YEAR,
            month = MOVIE_MONTH,
            status = MoviesStatus.Loaded,
            movies = paginationData.addPage(moviesPage)
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Reload)

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    status = MoviesStatus.Loaded,
                    movies = PaginationData()
                )
            )
        }
    }

    @Test
    fun `given a success action, when reduce is triggered, then the new state is the expected one`() {
        val paginationData = PaginationData<Movie>()
        val moviesPage = MoviesPage(SECOND_PAGE, firstPageItems)
        val previousState = MoviesState(
            year = null,
            month = null,
            status = MoviesStatus.Loading,
            movies = paginationData
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Success(moviesPage))

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    status = MoviesStatus.Loaded,
                    movies = paginationData.addPage(moviesPage)
                )
            )
        }
    }

    @Test
    fun `given an error action, when reduce is triggered, then the new state is the expected one`() {
        val paginationData = PaginationData<Movie>()
        val moviesPage = MoviesPage(SECOND_PAGE, firstPageItems)
        val previousState = MoviesState(
            year = null,
            month = null,
            status = MoviesStatus.Loading,
            movies = paginationData.addPage(moviesPage)
        )

        val result = MoviesReducer.reduce(previousState, MoviesAction.Error)

        expect {
            that(result).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    status = MoviesStatus.Loaded,
                    movies = paginationData.addPage(MoviesPage(SECOND_PAGE, emptyList()))
                )
            )
        }
    }

    @Test
    fun `given an error action, when once is triggered, then the side effect is the expected one`() {
        val result = MoviesReducer.once(MoviesAction.Error)

        expect { that(result).isEqualTo(MoviesSideEffect.Error) }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given an unexpected action, when once is triggered, then throw illegal argument exception`() {
        MoviesReducer.once(MoviesAction.Load(null, null))
    }

}
