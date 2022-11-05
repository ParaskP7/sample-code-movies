package io.petros.movies.app.robot.app.picker

import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.actions.ScopedActions
import io.petros.movies.app.robot.actions.UiActions
import io.petros.movies.app.robot.utils.idMatcher
import io.petros.movies.app.robot.utils.textMatcher
import io.petros.movies.lib.picker.R

class YearPickerRobot : ScopedActions(idMatcher(R.id.picker)) {

    fun inTitle(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.title)).action()

    fun inYear(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.year)).action()

    fun onActionOk(action: UiActions.() -> Actions) = UiActions(textMatcher(ACTION_OK)).action()

    companion object {

        const val MOVIE_YEAR_PICKER_TITLE = "Select Movie Year"

        private const val ACTION_OK = "OK"

    }

}
