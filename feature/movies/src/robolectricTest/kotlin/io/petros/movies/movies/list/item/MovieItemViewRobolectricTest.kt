package io.petros.movies.movies.list.item

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.android_test.runner.CustomRobolectricTestRunner
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull

@RunWith(CustomRobolectricTestRunner::class)
class MovieItemViewRobolectricTest {

    private val context = context()

    private val movie = movie()

    private val callbackMock = mockk<MovieItemCallback>()

    private val testedClass = MovieItemView(context())

    @Test
    fun `when the movie item view is instantiated, then the layout params are set`() {
        expect { that(testedClass.layoutParams).isNotNull() }
    }

    @Test
    fun `when movie is bind, then the item backdrop is set`() {
        expect { that(testedClass.binding.ivItemBackdrop.drawable).isNull() }

        testedClass.bind(movie)

        expect { that(testedClass.binding.ivItemBackdrop.drawable).isNotNull() }
    }

    @Test
    fun `when movie is bind, then the item title is set`() {
        testedClass.bind(movie)

        expect { that(testedClass.binding.tvItemTitle.text).isEqualTo(movie.title) }
    }

    @Test
    fun `when movie is bind, then the item release date is set`() {
        testedClass.bind(movie)

        expect { that(testedClass.binding.tvItemReleaseDate.text).isEqualTo(movie.releaseDate()) }
    }

    @Test
    fun `when movie is bind, then the item vote is set`() {
        testedClass.bind(movie)

        expect { that(testedClass.binding.tvItemVote.text).isEqualTo(movie.vote()) }
    }

    @Test
    fun `when movie callback is bind, then the callback's on click event is triggered`() {
        testedClass.bindCallback(movie, callbackMock)

        testedClass.performClick()

        verify { callbackMock.onClick(movie) }
    }

}
