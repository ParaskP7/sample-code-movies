package io.petros.movies.app.robot.app

import io.petros.movies.app.robot.Robot
import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.app.movie_details.MovieDetailsRobot
import io.petros.movies.app.robot.app.movies.MoviesRobot

class AppRobot : Robot(), Actions {

    fun inMovies(action: MoviesRobot.() -> Actions) = MoviesRobot().action()

    fun inMovieDetails(action: MovieDetailsRobot.() -> Actions) = MovieDetailsRobot().action()

}

fun robot(action: AppRobot.() -> Actions) = AppRobot().action()
