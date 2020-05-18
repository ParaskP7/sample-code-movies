package io.petros.movies.movie_details.navigator

import android.app.Activity
import android.content.Intent
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.petros.movies.android_test.app.TestApp
import io.petros.movies.movie_details.MovieDetailsFragment
import io.petros.movies.movie_details.navigator.MovieDetailsLauncherImpl.Companion.getMovie
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import strikt.api.expect
import strikt.assertions.isEqualTo

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class MovieDetailsLauncherImplRobolectricTest {

    private var activityMock = mockk<Activity>()
    private val testedClass = MovieDetailsLauncherImpl(activityMock)

    @Test
    fun `given a movie, when launch is called, then current activity starts target movies activity`() {
        val movie = movie()
        val slot = slot<Intent>()

        testedClass.launch(movie)

        verify { activityMock.startActivity(capture(slot)) }
        expect {
            that(slot.captured.component?.className).isEqualTo(MovieDetailsFragment::class.java.name)
            that(getMovie(slot.captured)).isEqualTo(movie)
        }
    }

    @Test
    fun `when finish is triggered, then the activity is finished`() {
        testedClass.finish()

        verify { activityMock.finish() }
    }

}
