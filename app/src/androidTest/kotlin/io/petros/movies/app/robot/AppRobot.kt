package io.petros.movies.app.robot

import io.petros.movies.android_test.robot.Robot
import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.app.robot.movie_details.MovieDetailsRobot
import io.petros.movies.app.robot.movies.MoviesRobot

class AppRobot : Robot(), Actions {

    fun inMovies(action: MoviesRobot.() -> Actions) = MoviesRobot().action()

    fun inMovieDetails(action: MovieDetailsRobot.() -> Actions) = MovieDetailsRobot().action()

}

fun robot(action: AppRobot.() -> Actions) = AppRobot().action()
