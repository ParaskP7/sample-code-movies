package io.petros.movies.movies.navigator

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.petros.movies.android_test.app.TestApp
import io.petros.movies.movies.MoviesActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import strikt.api.expect
import strikt.assertions.isEqualTo

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class MoviesLauncherImplRobolectricTest {

    private val slot = slot<Intent>()

    private var activityMock = mockk<AppCompatActivity>()
    private val testedClass = MoviesLauncherImpl(activityMock)

    @Test
    fun `when launch is called, then current activity starts target movies activity`() {
        testedClass.launch()

        verify { activityMock.startActivity(capture(slot)) }
        expect { that(slot.captured.component?.className).isEqualTo(MoviesActivity::class.java.name) }
    }

    @Test
    fun `when finish is triggered, then the activity is finished`() {
        testedClass.finish()

        verify { activityMock.finish() }
    }

}
