package io.petros.movies.presentation.feature.splash.navigator

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.presentation.feature.movies.navigator.MoviesLauncher
import org.junit.Test

class SplashActivityNavigatorTest {

    private val moviesLauncherMock = mockk<MoviesLauncher>()
    private val testedClass = SplashActivityNavigator(moviesLauncherMock)

    @Test
    fun `when navigating from splash activity, then movies activity launches`() {
        testedClass.navigate()

        verify { moviesLauncherMock.launch() }
    }

    @Test
    fun `when navigating from splash activity, then splash activity finishes`() {
        testedClass.navigate()

        verify { moviesLauncherMock.finish() }
    }

}
