package io.petros.movies.app.robot.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher

fun idMatcher(@IdRes resId: Int): () -> Matcher<View> = { withId(resId) }

fun textMatcher(text: String): () -> Matcher<View> = { withText(text) }
