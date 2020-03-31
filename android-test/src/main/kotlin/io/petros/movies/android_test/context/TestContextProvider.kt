package io.petros.movies.android_test.context

import android.content.Context
import androidx.test.core.app.ApplicationProvider

object TestContextProvider {

    fun context(): Context = ApplicationProvider.getApplicationContext()

}
