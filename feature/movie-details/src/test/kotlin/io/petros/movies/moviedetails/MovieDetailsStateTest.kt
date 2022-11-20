package io.petros.movies.moviedetails

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.test.domain.movie
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

class MovieDetailsStateTest {

    private val movie = movie()

    @Test
    fun `when init is triggered, then the initial state is the expected one`() {
        val result = MovieDetailsReducer.init()

        expect {
            that(result).isEqualTo(
                MovieDetailsState(
                    status = MovieDetailsStatus.Init,
                    movie = Movie.Default,
                )
            )
        }
    }

    @Test
    fun `given an idle action, when reduce is triggered, then the new state is the expected one`() {
        val previousState = MovieDetailsState(
            status = MovieDetailsStatus.Init,
            movie = Movie.Default,
        )

        val result = MovieDetailsReducer.reduce(previousState, MovieDetailsAction.Idle)

        expect {
            that(result).isEqualTo(
                MovieDetailsState(
                    status = MovieDetailsStatus.Idle,
                    movie = Movie.Default,
                )
            )
        }
    }

    @Test
    fun `given a load action, when reduce is triggered, then the new state is the expected one`() {
        val previousState = MovieDetailsState(
            status = MovieDetailsStatus.Idle,
            movie = Movie.Default,
        )

        val result = MovieDetailsReducer.reduce(previousState, MovieDetailsAction.Load)

        expect {
            that(result).isEqualTo(
                MovieDetailsState(
                    status = MovieDetailsStatus.Loading,
                    movie = Movie.Default,
                )
            )
        }
    }

    @Test
    fun `given a success action, when reduce is triggered, then the new state is the expected one`() {
        val previousState = MovieDetailsState(
            status = MovieDetailsStatus.Loading,
            movie = Movie.Default,
        )

        val result = MovieDetailsReducer.reduce(previousState, MovieDetailsAction.Success(movie))

        expect {
            that(result).isEqualTo(
                MovieDetailsState(
                    status = MovieDetailsStatus.Loaded,
                    movie = movie,
                )
            )
        }
    }

    @Test
    fun `given an error action, when reduce is triggered, then the new state is the expected one`() {
        val previousState = MovieDetailsState(
            status = MovieDetailsStatus.Loading,
            movie = Movie.Default,
        )

        val result = MovieDetailsReducer.reduce(previousState, MovieDetailsAction.Error)

        expect {
            that(result).isEqualTo(
                MovieDetailsState(
                    status = MovieDetailsStatus.Loaded,
                    movie = Movie.Default,
                )
            )
        }
    }

    @Test
    fun `given an error action, when once is triggered, then the side effect is the expected one`() {
        val result = MovieDetailsReducer.once(MovieDetailsAction.Error)

        expect { that(result).isEqualTo(MovieDetailsSideEffect.Error) }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given an unexpected action, when once is triggered, then throw illegal argument exception`() {
        MovieDetailsReducer.once(MovieDetailsAction.Load)
    }

}
