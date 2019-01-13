package io.petros.movies.presentation.feature.movies.view

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.petros.movies.presentation.RobolectricTestProvider.Companion.provideContext
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import kotlinx.android.synthetic.main.item_movie.view.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull

@RunWith(RobolectricTestRunner::class)
class MovieItemViewRobolectricTest {

    private val movie = provideMovie()

    private val callbackMock = mock<MovieCallback>()

    private lateinit var testedClass: MovieItemView
    private val context = provideContext()

    @Before
    fun setUp() {
        testedClass = MovieItemView(context)
    }

    @Test
    fun `When movie is bind, then the movie backdrop is set`() {
        expectThat(testedClass.iv_movie_backdrop.drawable).isNull()

        testedClass.bind(movie)

        expectThat(testedClass.iv_movie_backdrop.drawable).isNotNull()
    }

    @Test
    fun `When movie is bind, then the movie title is set`() {
        testedClass.bind(movie)

        expectThat(testedClass.tv_movie_title.text).isEqualTo(movie.title)
    }

    @Test
    fun `When movie is bind, then the movie release date is set`() {
        testedClass.bind(movie)

        expectThat(testedClass.tv_movie_release_date.text).isEqualTo(movie.releaseDate())
    }

    @Test
    fun `When movie is bind, then the movie vote is set`() {
        testedClass.bind(movie)

        expectThat(testedClass.tv_movie_vote.text).isEqualTo(movie.vote())
    }

    @Test
    fun `When movie callback is bind, then the callback's on click event is triggered`() {
        testedClass.bindCallback(movie, callbackMock)

        testedClass.performClick()

        val sharedElementMovie = SharedElementMovie(movie, testedClass.iv_movie_backdrop)
        verify(callbackMock).onClick(sharedElementMovie)
    }

}
