package io.petros.movies.androidtest.runner

import android.os.Build
import io.petros.movies.androidtest.app.TestApp
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

class CustomRobolectricTestRunner(
    testClass: Class<Any>,
) : RobolectricTestRunner(testClass) {

    override fun buildGlobalConfig(): Config {
        return Config.Builder()
            .setApplication(TestApp::class.java)
            .setSdk(Build.VERSION_CODES.TIRAMISU)
            .build()
    }

}
