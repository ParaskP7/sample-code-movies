package io.petros.movies.presentation.feature.movie.navigator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.petros.movies.presentation.feature.movie.MovieDetailsActivity
import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsActivityLauncher.Companion.getMovie
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isEqualTo

@RunWith(RobolectricTestRunner::class)
class MovieDetailsActivityLauncherRobolectricTest {

    private val movie = movie()

    private val slot = slot<Intent>()

    private var appCompatActivityMock = mockk<AppCompatActivity>()
    private val testedClass = MovieDetailsActivityLauncher(appCompatActivityMock)

    @Test
    fun `when launch is called, then current activity starts target movies activity`() {
        testedClass.launch(movie)

        verify { appCompatActivityMock.startActivity(capture(slot)) }
        expect {
            that(slot.captured.component?.className).isEqualTo(MovieDetailsActivity::class.java.name)
            that(getMovie(slot.captured)).isEqualTo(movie)
        }
    }

}
