package io.petros.movies.app.robot.actions

import android.view.View
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf
import timber.log.Timber

open class UiActions(
    private val matcher: () -> Matcher<View>
) : ScopedActions(matcher) {

    fun performClick(): Actions {
        onView(matcher())
            .perform(click())
        return NoActions
    }

    fun hasText(
        text: String
    ): Actions {
        onView(withText(text))
            .check(matches(isDisplayed()))
        return NoActions
    }

    @Suppress("SwallowedException")
    fun hasTextIgnoreMultipleViews(
        text: String
    ): Actions {
        try {
            onView(withText(text))
                .check(matches(isDisplayed()))
        } catch (avme: AmbiguousViewMatcherException) {
            Timber.w("This text matches multiple views in the hierarchy. [Text: $text]")
        }
        return NoActions
    }

    fun hasTextInside(
        text: String
    ): Actions {
        onView(allOf(withText(text), anyOf(isDescendantOfA(matcher()), matcher())))
            .check(matches(isDisplayed()))
        return NoActions
    }

}
