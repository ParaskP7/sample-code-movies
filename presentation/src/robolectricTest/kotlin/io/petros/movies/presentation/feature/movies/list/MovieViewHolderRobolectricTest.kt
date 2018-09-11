package io.petros.movies.presentation.feature.movies.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.presentation.PreconfiguredRobolectricTestRunner
import io.petros.movies.presentation.feature.movies.view.MovieItemView
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(PreconfiguredRobolectricTestRunner::class)
class MovieViewHolderRobolectricTest {

    private val movie = provideMovie()

    private lateinit var testedClass: MovieViewHolder
    private val itemViewMock = mock<MovieItemView>()

    @Before
    fun setUp() {
        testedClass = MovieViewHolder(itemViewMock)
    }

    @Test
    fun `When the view holder binds a movie, then the item view is bind with a movie`() {
        testedClass.bind(movie)

        verify(itemViewMock).bind(movie)
    }

}
