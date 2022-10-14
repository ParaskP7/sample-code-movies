package io.petros.movies.app.robot.app.movies

import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.actions.ScopedActions
import io.petros.movies.app.robot.actions.UiActions
import io.petros.movies.app.robot.utils.idMatcher
import io.petros.movies.feature.movies.R

@Suppress("ForbiddenComment")
class MoviesToolbarRobot : ScopedActions(idMatcher(R.id.toolbar)) {

    fun inFilterIcon(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.ivToolbarFilterIcon)).action()

    fun inCloseIcon(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.ivToolbarCloseIcon)).action()

    fun inYearFilter(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvToolbarFilterYear)).action()

    fun inMonthFilter(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvToolbarFilterMonth)).action()

    companion object {

        const val MOVIES_TOOLBAR_FILTER_YEAR = "Year"
        const val MOVIES_TOOLBAR_FILTER_MONTH = "Month"

        // TODO: Revert to original value by specifically selecting the year/month during testing
        @Suppress("unused") const val MOVIES_TOOLBAR_FILTER_YEAR_2020 = "2020"
        @Suppress("unused") const val MOVIES_TOOLBAR_FILTER_MONTH_APRIL = "April"
        const val MOVIES_TOOLBAR_FILTER_YEAR_VALUE = "2022"
        const val MOVIES_TOOLBAR_FILTER_MONTH_VALUE = "October"

    }

}
