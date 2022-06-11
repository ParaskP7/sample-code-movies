package io.petros.movies.app.robot.app.picker

import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.actions.ScopedActions
import io.petros.movies.app.robot.actions.UiActions
import io.petros.movies.app.robot.utils.idMatcher
import io.petros.movies.app.robot.utils.textMatcher
import io.petros.movies.lib.picker.R

@Suppress("ForbiddenComment")
class MonthPickerRobot : ScopedActions(idMatcher(R.id.picker)) {

    fun inTitle(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.title)).action()

    fun inMonth(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.month)).action()

    fun onActionOk(action: UiActions.() -> Actions) = UiActions(textMatcher(ACTION_OK)).action()

    companion object {

        const val MOVIE_MONTH_PICKER_TITLE = "Select Movie Month"

        // TODO: Revert to original value by specifically selecting the month during testing
        @Suppress("unused") const val MOVIE_MONTH_PICKER_MONTH_APR = "Apr"
        const val MOVIE_MONTH_PICKER_MONTH_VALUE = "Jun"

        private const val ACTION_OK = "OK"

    }

}
