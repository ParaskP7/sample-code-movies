package io.petros.movies.app.robot.actions

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

open class ScopedActions(
    private val matcher: () -> Matcher<View>,
) : Actions {

    fun isShown(): Actions {
        onView(matcher())
            .check(matches(isDisplayed()))
        return NoActions
    }

    fun isNotShown(): Actions {
        onView(matcher())
            .check(matches(not(isDisplayed())))
        return NoActions
    }

}
