package io.petros.movies.movies.navigator

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.movie_details.navigator.MovieDetailsLauncher
import io.petros.movies.test.domain.movie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class MoviesNavigatorImplSpek : Spek({

    val movieDetailsLauncherMock = mockk<MovieDetailsLauncher>()

    Feature("Movies navigator") {
        val testedClass by memoized { MoviesNavigatorImpl(movieDetailsLauncherMock) }
        Scenario("navigating from movies") {
            val movie = movie()
            When("navigating from movies activity") {
                testedClass.navigate(movie)
            }
            Then("movie details activity launches with it") {
                verify { movieDetailsLauncherMock.launch(movie) }
            }
        }
    }

})
