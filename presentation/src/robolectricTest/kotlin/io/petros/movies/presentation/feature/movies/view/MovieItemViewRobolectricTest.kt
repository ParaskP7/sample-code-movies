package io.petros.movies.presentation.feature.movies.view

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.utils.RobolectricTestProvider.Companion.provideContext
import io.petros.movies.test.domain.provideMovie
import kotlinx.android.synthetic.main.item_movie.view.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull

@RunWith(RobolectricTestRunner::class)
class MovieItemViewRobolectricTest {

    private val movie = provideMovie()

    private val callbackMock = mockk<MovieCallback>()

    private val context = provideContext()
    private val testedClass = MovieItemView(context)

    @Test
    fun `when movie is bind, then the movie backdrop is set`() {
        expect { that(testedClass.iv_movie_backdrop.drawable).isNull() }

        testedClass.bind(movie)

        expect { that(testedClass.iv_movie_backdrop.drawable).isNotNull() }
    }

    @Test
    fun `when movie is bind, then the movie title is set`() {
        testedClass.bind(movie)

        expect { that(testedClass.tv_movie_title.text).isEqualTo(movie.title) }
    }

    @Test
    fun `when movie is bind, then the movie release date is set`() {
        testedClass.bind(movie)

        expect { that(testedClass.tv_movie_release_date.text).isEqualTo(movie.releaseDate()) }
    }

    @Test
    fun `when movie is bind, then the movie vote is set`() {
        testedClass.bind(movie)

        expect { that(testedClass.tv_movie_vote.text).isEqualTo(movie.vote()) }
    }

    @Test
    fun `when movie callback is bind, then the callback's on click event is triggered`() {
        testedClass.bindCallback(movie, callbackMock)
        val sharedElementMovie = SharedElementMovie(movie, testedClass.iv_movie_backdrop)

        testedClass.performClick()

        verify { callbackMock.onClick(sharedElementMovie) }
    }

}
