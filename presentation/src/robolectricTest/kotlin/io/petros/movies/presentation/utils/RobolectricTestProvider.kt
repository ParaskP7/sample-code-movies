package io.petros.movies.presentation.utils

import androidx.test.core.app.ApplicationProvider
import io.petros.movies.presentation.TestApp

object RobolectricTestProvider {

    internal fun context() = ApplicationProvider.getApplicationContext<TestApp>()

}
