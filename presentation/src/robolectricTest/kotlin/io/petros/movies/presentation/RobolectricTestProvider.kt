package io.petros.movies.presentation

import android.support.v7.app.AppCompatActivity
import org.robolectric.Robolectric
import org.robolectric.shadows.ShadowApplication

class RobolectricTestProvider {

    companion object {

        internal fun provideContext() = ShadowApplication.getInstance().applicationContext!!

        internal fun provideActivity() = Robolectric.setupActivity(AppCompatActivity::class.java)

    }

}
