package io.petros.movies.androidtest.context

import android.content.Context
import androidx.test.core.app.ApplicationProvider

object TestContextProvider {

    fun context(): Context = ApplicationProvider.getApplicationContext()

}
