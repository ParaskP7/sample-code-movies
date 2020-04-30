package io.petros.movies.android_test.robot

import androidx.test.espresso.Espresso.pressBack
import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.android_test.robot.actions.NoActions

open class Robot : Actions {

    fun performBack(): Actions {
        pressBack()
        return NoActions
    }

}
