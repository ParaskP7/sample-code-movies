package io.petros.movies.app.robot.movie_details

import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.android_test.robot.actions.ScopedActions
import io.petros.movies.android_test.robot.actions.UiActions
import io.petros.movies.android_test.robot.utils.idMatcher
import io.petros.movies.feature.movie.details.R

class MovieDetailsScreenRobot : ScopedActions(idMatcher(R.id.ctrMovieDetails)) {

    fun inTitle(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvTitle)).action()

    fun inReleaseDate(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvReleaseDate)).action()

    fun inVote(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvVote)).action()

    fun inOverview(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvOverview)).action()

}
