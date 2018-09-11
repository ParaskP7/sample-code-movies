package io.petros.movies.presentation

import io.petros.movies.BuildConfig
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * A {@link RobolectricTestRunner} that allows us to set robolectric configuration in one place instead of setting it in
 * each test class via {@link Config}.
 */
class PreconfiguredRobolectricTestRunner(testClass: Class<*>) : RobolectricTestRunner(testClass) {

    companion object {

        /**
         * The current version of Robolectric (3.8) does not support the API level 28. Thus, and until it is upgraded to
         * 4.0, in order for Robolectric to work it is required that the SDK is set to a lower level (ie. API level 27).
         */
        private const val SDK_API_LEVEL_TO_EMULATE = 27

    }

    override fun buildGlobalConfig(): Config {
        return Config.Builder()
            .setSdk(SDK_API_LEVEL_TO_EMULATE)
            .setConstants(BuildConfig::class.java)
            .build()
    }

}
