package io.petros.movies.movies.navigator

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.core.view.SharedElementMovie
import io.petros.movies.movie_details.navigator.MovieDetailsLauncher
import io.petros.movies.test.domain.movie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class MoviesActivityNavigatorSpek : Spek({

    val movieDetailsLauncherMock = mockk<MovieDetailsLauncher>()

    Feature("Movies activity navigator") {
        val testedClass by memoized { MoviesActivityNavigator(movieDetailsLauncherMock) }
        Scenario("navigating from movies") {
            val movie = movie()
            var sharedElementMovie: SharedElementMovie? = null
            Given("shared element movie") {
                sharedElementMovie = SharedElementMovie(movie, mockk())
            }
            When("navigating from movies activity") {
                sharedElementMovie?.let { testedClass.navigate(it) }
            }
            Then("movie details activity launches with it") {
                sharedElementMovie?.let { verify { movieDetailsLauncherMock.launch(it) } }
            }
        }
    }

})
