package io.petros.movies.app.robot.movies

import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.android_test.robot.actions.ScopedActions
import io.petros.movies.android_test.robot.actions.UiActions
import io.petros.movies.android_test.robot.utils.idMatcher
import io.petros.movies.movies.R

class MoviesToolbarRobot : ScopedActions(idMatcher(R.id.ctrMoviesToolbar)) {

    companion object {

        const val MOVIES_TOOLBAR_LABEL = "Movies"
        const val MOVIES_TOOLBAR_FILTER_YEAR = "Year"
        const val MOVIES_TOOLBAR_FILTER_YEAR_2020 = "2020"
        const val MOVIES_TOOLBAR_FILTER_MONTH = "Month"
        const val MOVIES_TOOLBAR_FILTER_MONTH_APRIL = "April"

    }

    fun inLabel(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvToolbarMoviesLabel)).action()

    fun inFilterIcon(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.ivToolbarFilterIcon)).action()

    fun inCloseIcon(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.ivToolbarCloseIcon)).action()

    fun inYearFilter(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvToolbarFilterYear)).action()

    fun inMonthFilter(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvToolbarFilterMonth)).action()

}
