package io.petros.movies.movies.view

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.core.view.SharedElementMovie
import io.petros.movies.movies.listener.MovieCallback
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull

@RunWith(RobolectricTestRunner::class)
class MovieItemViewRobolectricTest {

    private val movie = movie()

    private val callbackMock = mockk<MovieCallback>()

    private val testedClass = MovieItemView(context())

    @Test
    fun `when movie is bind, then the movie backdrop is set`() {
        expect { that(testedClass.binding.ivMovieBackdrop.drawable).isNull() }

        testedClass.bind(movie)

        expect { that(testedClass.binding.ivMovieBackdrop.drawable).isNotNull() }
    }

    @Test
    fun `when movie is bind, then the movie title is set`() {
        testedClass.bind(movie)

        expect { that(testedClass.binding.tvMovieTitle.text).isEqualTo(movie.title) }
    }

    @Test
    fun `when movie is bind, then the movie release date is set`() {
        testedClass.bind(movie)

        expect { that(testedClass.binding.tvMovieReleaseDate.text).isEqualTo(movie.releaseDate()) }
    }

    @Test
    fun `when movie is bind, then the movie vote is set`() {
        testedClass.bind(movie)

        expect { that(testedClass.binding.tvMovieVote.text).isEqualTo(movie.vote()) }
    }

    @Test
    fun `when movie callback is bind, then the callback's on click event is triggered`() {
        testedClass.bindCallback(movie, callbackMock)
        val sharedElementMovie = SharedElementMovie(movie, testedClass.binding.ivMovieBackdrop)

        testedClass.performClick()

        verify { callbackMock.onClick(sharedElementMovie) }
    }

}
