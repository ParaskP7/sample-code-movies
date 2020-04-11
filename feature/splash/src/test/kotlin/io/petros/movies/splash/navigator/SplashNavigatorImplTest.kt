package io.petros.movies.splash.navigator

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.movies.navigator.MoviesLauncher
import org.junit.Test

class SplashNavigatorImplTest {

    private val moviesLauncherMock = mockk<MoviesLauncher>()
    private val testedClass = SplashNavigatorImpl(moviesLauncherMock)

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
