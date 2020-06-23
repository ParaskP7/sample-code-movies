package io.petros.movies.app.robot.app.movies

import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.actions.ScopedActions
import io.petros.movies.app.robot.app.picker.MonthPickerRobot
import io.petros.movies.app.robot.app.picker.YearPickerRobot
import io.petros.movies.app.robot.utils.idMatcher
import io.petros.movies.feature.movies.R

class MoviesRobot : ScopedActions(idMatcher(R.id.ctrMovies)) {

    fun inToolbar(action: MoviesToolbarRobot.() -> Actions) = MoviesToolbarRobot().action()

    fun inYearPicker(action: YearPickerRobot.() -> Actions) = YearPickerRobot().action()

    fun inMonthPicker(action: MonthPickerRobot.() -> Actions) = MonthPickerRobot().action()

    fun inList(action: MoviesListRobot.() -> Actions) = MoviesListRobot().action()

}
