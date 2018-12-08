package io.petros.movies.presentation

import androidx.appcompat.app.AppCompatActivity
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment

class RobolectricTestProvider {

    companion object {

        internal fun provideContext() = RuntimeEnvironment.application.applicationContext

        internal fun provideActivity() = Robolectric.setupActivity(AppCompatActivity::class.java)

    }

}
