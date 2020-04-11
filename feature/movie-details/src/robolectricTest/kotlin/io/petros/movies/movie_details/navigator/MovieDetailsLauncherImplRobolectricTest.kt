package io.petros.movies.movie_details.navigator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.petros.movies.movie_details.MovieDetailsActivity
import io.petros.movies.movie_details.navigator.MovieDetailsLauncherImpl.Companion.getMovie
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isEqualTo

@RunWith(RobolectricTestRunner::class)
class MovieDetailsLauncherImplRobolectricTest {

    private var activityMock = mockk<AppCompatActivity>()
    private val testedClass = MovieDetailsLauncherImpl(activityMock)

    @Test
    fun `when launch is called, then current activity starts target movies activity`() {
        val movie = movie()
        val slot = slot<Intent>()

        testedClass.launch(movie)

        verify { activityMock.startActivity(capture(slot)) }
        expect {
            that(slot.captured.component?.className).isEqualTo(MovieDetailsActivity::class.java.name)
            that(getMovie(slot.captured)).isEqualTo(movie)
        }
    }

}
