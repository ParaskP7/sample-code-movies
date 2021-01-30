package io.petros.movies.app.robot.app.movies

import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.actions.ScopedActions
import io.petros.movies.app.robot.actions.UiActions
import io.petros.movies.app.robot.utils.idMatcher
import io.petros.movies.feature.movies.R

@Suppress("ForbiddenComment")
class MoviesToolbarRobot : ScopedActions(idMatcher(R.id.toolbar)) {

    companion object {

        const val MOVIES_TOOLBAR_FILTER_YEAR = "Year"

        // TODO: Revert to '2020' by specifically selecting this year during testing
        @Suppress("unused") const val MOVIES_TOOLBAR_FILTER_YEAR_2020 = "2020"
        const val MOVIES_TOOLBAR_FILTER_YEAR_2021 = "2021"
        const val MOVIES_TOOLBAR_FILTER_MONTH = "Month"

        // TODO: Revert to 'April' by specifically selecting this month during testing
        @Suppress("unused") const val MOVIES_TOOLBAR_FILTER_MONTH_APRIL = "April"
        const val MOVIES_TOOLBAR_FILTER_MONTH_JANUARY = "January"

    }

    fun inFilterIcon(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.ivToolbarFilterIcon)).action()

    fun inCloseIcon(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.ivToolbarCloseIcon)).action()

    fun inYearFilter(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvToolbarFilterYear)).action()

    fun inMonthFilter(action: UiActions.() -> Actions) = UiActions(idMatcher(R.id.tvToolbarFilterMonth)).action()

}
