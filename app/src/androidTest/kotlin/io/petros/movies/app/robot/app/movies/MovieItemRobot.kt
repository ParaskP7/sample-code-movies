package io.petros.movies.app.robot.app.movies

import android.view.View
import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.actions.UiActions
import io.petros.movies.app.robot.utils.idMatcher
import io.petros.movies.feature.movies.R
import org.hamcrest.Matcher

class MovieItemRobot(matcher: () -> Matcher<View>) : UiActions(matcher) {

    fun inTitle(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvItemTitle)).action()

    fun inReleaseDate(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvItemReleaseDate)).action()

    fun inVote(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvItemVote)).action()

}
