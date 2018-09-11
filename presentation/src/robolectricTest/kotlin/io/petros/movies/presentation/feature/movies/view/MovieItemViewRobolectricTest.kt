package io.petros.movies.presentation.feature.movies.view

import io.petros.movies.presentation.PreconfiguredRobolectricTestRunner
import io.petros.movies.presentation.RobolectricTestProvider.Companion.provideContext
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import kotlinx.android.synthetic.main.item_movie.view.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(PreconfiguredRobolectricTestRunner::class)
class MovieItemViewRobolectricTest {

    private val movie = provideMovie()

    private lateinit var testedClass: MovieItemView
    private val context = provideContext()

    @Before
    fun setUp() {
        testedClass = MovieItemView(context)
    }

    @Test
    fun `When movie is bind, then the movie title is set`() {
        testedClass.bind(movie)

        assertThat(testedClass.tv_movie_title.text).isEqualTo(movie.title)
    }

}
