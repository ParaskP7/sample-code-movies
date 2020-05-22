package io.petros.movies.app.robot.movie_details

import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.android_test.robot.actions.ScopedActions
import io.petros.movies.android_test.robot.utils.idMatcher
import io.petros.movies.feature.movie.details.R

class MovieDetailsRobot : ScopedActions(idMatcher(R.id.ctrMovieDetails)) {

    fun inScreen(action: MovieDetailsScreenRobot.() -> Actions) = MovieDetailsScreenRobot().action()

}
