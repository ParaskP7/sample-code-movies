package io.petros.movies.app.robot.movies

import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.android_test.robot.actions.ScopedActions
import io.petros.movies.android_test.robot.utils.idMatcher
import io.petros.movies.app.robot.picker.MonthPickerRobot
import io.petros.movies.app.robot.picker.YearPickerRobot
import io.petros.movies.movies.R

class MoviesRobot : ScopedActions(idMatcher(R.id.ctrMovies)) {

    fun inToolbar(action: MoviesToolbarRobot.() -> Actions) = MoviesToolbarRobot().action()

    fun inYearPicker(action: YearPickerRobot.() -> Actions) = YearPickerRobot().action()

    fun inMonthPicker(action: MonthPickerRobot.() -> Actions) = MonthPickerRobot().action()

    fun inList(action: MoviesListRobot.() -> Actions) = MoviesListRobot().action()

}
