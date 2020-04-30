package io.petros.movies.app.robot.movie_details

import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.android_test.robot.actions.ScopedActions
import io.petros.movies.android_test.robot.actions.UiActions
import io.petros.movies.android_test.robot.utils.idMatcher
import io.petros.movies.movie_details.R

class MovieDetailsToolbarRobot : ScopedActions(idMatcher(R.id.ctrMovieDetailsToolbar)) {

    companion object {

        const val MOVIE_DETAILS_TOOLBAR_LABEL = "Movie Details"

    }

    fun inLabel(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvToolbarMovieDetailsLabel)).action()

}
