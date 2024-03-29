package io.petros.movies.app.robot.app.picker

import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.actions.ScopedActions
import io.petros.movies.app.robot.actions.UiActions
import io.petros.movies.app.robot.utils.idMatcher
import io.petros.movies.app.robot.utils.textMatcher
import io.petros.movies.lib.picker.R

class MonthPickerRobot : ScopedActions(idMatcher(R.id.picker)) {

    fun inTitle(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.title)).action()

    fun inMonth(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.month)).action()

    fun onActionOk(action: UiActions.() -> Actions) = UiActions(textMatcher(ACTION_OK)).action()

    companion object {

        const val MOVIE_MONTH_PICKER_TITLE = "Select Movie Month"

        private const val ACTION_OK = "OK"

    }

}
