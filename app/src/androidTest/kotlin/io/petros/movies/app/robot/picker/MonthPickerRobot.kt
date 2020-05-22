package io.petros.movies.app.robot.picker

import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.android_test.robot.actions.ScopedActions
import io.petros.movies.android_test.robot.actions.UiActions
import io.petros.movies.android_test.robot.utils.idMatcher
import io.petros.movies.android_test.robot.utils.textMatcher
import io.petros.movies.feature.picker.R

@Suppress("ForbiddenComment")
class MonthPickerRobot : ScopedActions(idMatcher(R.id.monthPicker)) {

    companion object {

        const val MOVIE_MONTH_PICKER_TITLE = "Select Movie Month"

        // TODO: Revert to 'Apr' by specifically selecting the month during testing
        @Suppress("unused") const val MOVIE_MONTH_PICKER_MONTH_APR = "Apr"
        const val MOVIE_MONTH_PICKER_MONTH_MAY = "May"

        private const val ACTION_OK = "OK"

    }

    fun inTitle(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.title)).action()

    fun inMonth(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.month)).action()

    fun onActionOk(action: UiActions.() -> Actions) = UiActions(textMatcher(ACTION_OK)).action()

}
