package io.petros.movies.splash.navigator

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.movies.navigator.MoviesLauncher
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class SplashNavigatorImplSpek : Spek({

    val moviesLauncherMock = mockk<MoviesLauncher>()

    Feature("Splash navigator") {
        val testedClass by memoized { SplashNavigatorImpl(moviesLauncherMock) }
        Scenario("navigating from splash") {
            When("navigating from splash activity") {
                testedClass.navigate()
            }
            Then("movies activity launches") {
                verify { moviesLauncherMock.launch() }
            }
            Then("splash activity finishes") {
                verify { moviesLauncherMock.finish() }
            }
        }
    }

})
