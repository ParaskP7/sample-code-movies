package io.petros.movies.presentation

import androidx.test.core.app.ApplicationProvider

class RobolectricTestProvider {

    companion object {

        internal fun provideContext() = ApplicationProvider.getApplicationContext<TestApp>()

    }

}
