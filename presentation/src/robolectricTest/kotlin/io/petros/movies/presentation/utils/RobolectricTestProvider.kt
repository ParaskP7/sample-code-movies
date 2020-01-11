package io.petros.movies.presentation.utils

import androidx.test.core.app.ApplicationProvider
import io.petros.movies.presentation.TestApp

class RobolectricTestProvider {

    companion object {

        internal fun provideContext() = ApplicationProvider.getApplicationContext<TestApp>()

    }

}
