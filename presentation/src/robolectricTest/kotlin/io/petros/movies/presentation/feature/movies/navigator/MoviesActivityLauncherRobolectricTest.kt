package io.petros.movies.presentation.feature.movies.navigator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.presentation.PreconfiguredRobolectricTestRunner
import io.petros.movies.presentation.feature.movies.MoviesActivity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor

@RunWith(PreconfiguredRobolectricTestRunner::class)
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
        assertThat(intentCaptor.value?.component?.className).isEqualTo(MoviesActivity::class.java.name)
    }

}
