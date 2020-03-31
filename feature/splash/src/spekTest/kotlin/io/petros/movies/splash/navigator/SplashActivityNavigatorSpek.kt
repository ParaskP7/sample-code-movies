package io.petros.movies.splash.navigator

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.movies.navigator.MoviesLauncher
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class SplashActivityNavigatorSpek : Spek({

    val moviesLauncherMock = mockk<MoviesLauncher>()

    Feature("Splash activity navigator") {
        val testedClass by memoized { SplashActivityNavigator(moviesLauncherMock) }
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
