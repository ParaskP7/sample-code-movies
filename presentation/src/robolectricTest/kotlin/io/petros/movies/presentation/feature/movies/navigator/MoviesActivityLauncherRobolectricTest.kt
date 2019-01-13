package io.petros.movies.presentation.feature.movies.navigator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.petros.movies.presentation.feature.movies.MoviesActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isEqualTo

@RunWith(RobolectricTestRunner::class)
class MoviesActivityLauncherRobolectricTest {

    private val intentCaptor = ArgumentCaptor.forClass(Intent::class.java)

    private lateinit var testedClass: MoviesActivityLauncher
    private var appCompatActivityMock = mock<AppCompatActivity>()

    @Before
    fun setUp() {
        testedClass = MoviesActivityLauncher(appCompatActivityMock)
    }

    @Test
    fun `When launch is called, then current activity starts target movies activity`() {
        testedClass.launch()

        verify(appCompatActivityMock).startActivity(intentCaptor.capture())
        expect { that(intentCaptor.value?.component?.className).isEqualTo(MoviesActivity::class.java.name) }
    }

}
