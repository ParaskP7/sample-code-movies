package io.petros.movies.app.robot.picker

import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.android_test.robot.actions.ScopedActions
import io.petros.movies.android_test.robot.actions.UiActions
import io.petros.movies.android_test.robot.utils.idMatcher
import io.petros.movies.android_test.robot.utils.textMatcher
import io.petros.movies.picker.R

class MonthPickerRobot : ScopedActions(idMatcher(R.id.monthPicker)) {

    companion object {

        const val MOVIE_MONTH_PICKER_TITLE = "Select Movie Month"
        const val MOVIE_MONTH_PICKER_MONTH_APR = "Apr"

        private const val ACTION_OK = "OK"

    }

    fun inTitle(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.title)).action()

    fun inMonth(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.month)).action()

    fun onActionOk(action: UiActions.() -> Actions) = UiActions(textMatcher(ACTION_OK)).action()

}
