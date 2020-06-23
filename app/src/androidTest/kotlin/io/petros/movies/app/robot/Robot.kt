package io.petros.movies.app.robot

import androidx.test.espresso.Espresso.pressBack
import io.petros.movies.app.robot.actions.Actions
import io.petros.movies.app.robot.actions.NoActions

open class Robot : Actions {

    fun performBack(): Actions {
        pressBack()
        return NoActions
    }

}
