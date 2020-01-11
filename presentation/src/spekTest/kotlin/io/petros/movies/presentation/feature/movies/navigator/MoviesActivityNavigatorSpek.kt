package io.petros.movies.presentation.feature.movies.navigator

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsLauncher
import io.petros.movies.presentation.feature.movies.view.SharedElementMovie
import io.petros.movies.test.domain.provideMovie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class MoviesActivityNavigatorSpek : Spek({

    val movie = provideMovie()
    val movieDetailsLauncherMock = mockk<MovieDetailsLauncher>()

    Feature("Movies activity navigator") {
        val testedClass by memoized { MoviesActivityNavigator(movieDetailsLauncherMock) }
        Scenario("navigating from movies") {
            lateinit var sharedElementMovie: SharedElementMovie
            Given("a shared element movie") {
                sharedElementMovie = SharedElementMovie(movie, mockk())
            }
            When("navigating from movies activity") {
                testedClass.navigate(sharedElementMovie)
            }
            Then("movie details activity launches with a shared element movie") {
                verify { movieDetailsLauncherMock.launch(sharedElementMovie) }
            }
        }
    }

})
