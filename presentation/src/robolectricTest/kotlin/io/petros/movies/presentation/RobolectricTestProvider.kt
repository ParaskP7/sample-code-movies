package io.petros.movies.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ApplicationProvider
import org.robolectric.Robolectric

class RobolectricTestProvider {

    companion object {

        internal fun provideContext() = ApplicationProvider.getApplicationContext<TestApp>()

        internal fun provideActivity() = Robolectric.setupActivity(AppCompatActivity::class.java)

    }

}
