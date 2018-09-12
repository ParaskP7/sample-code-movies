package io.petros.movies.presentation.feature.movies.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.presentation.PreconfiguredRobolectricTestRunner
import io.petros.movies.presentation.RobolectricTestProvider.Companion.provideContext
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.feature.movies.view.MovieItemView
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(PreconfiguredRobolectricTestRunner::class)
class MovieViewHolderRobolectricTest {

    private val context = provideContext()

    private val movie = provideMovie()

    private lateinit var testedClassWithMockedItem: MovieViewHolder
    private lateinit var testedClassWithoutMockedItem: MovieViewHolder
    private val itemViewMock = mock<MovieItemView>()
    private val callbackMock = mock<MovieCallback>()

    @Before
    fun setUp() {
        testedClassWithMockedItem = MovieViewHolder(itemViewMock, callbackMock)
        testedClassWithoutMockedItem = MovieViewHolder(MovieItemView(context), callbackMock)
    }

    @Test
    fun `When the view holder binds a movie, then the item view is bind with a movie`() {
        testedClassWithMockedItem.bind(movie)

        verify(itemViewMock).bind(movie)
    }

    @Test
    fun `When the item view is clicked, then the callback's on click event is triggered`() {
        testedClassWithoutMockedItem.bind(movie)

        testedClassWithoutMockedItem.itemView.performClick()

        verify(callbackMock).onClick(movie)
    }

}
