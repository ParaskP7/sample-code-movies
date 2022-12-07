package io.petros.movies.app.robot.app.moviedetails

import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.actions.ScopedActions
import io.petros.movies.app.robot.utils.idMatcher
import io.petros.movies.feature.movie.details.R

class MovieDetailsRobot : ScopedActions(idMatcher(R.id.ctrMovieDetails)) {

    fun inScreen(action: MovieDetailsScreenRobot.() -> Actions) = MovieDetailsScreenRobot().action()

}
