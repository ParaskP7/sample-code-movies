package io.petros.movies.app

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.petros.movies.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import strikt.api.expect
import strikt.assertions.isEqualTo

@RunWith(AndroidJUnit4::class)
class AppInstrumentedTest {

    @Test
    fun useAppContext() {
        val result = ApplicationProvider.getApplicationContext<App>()

        expect { that(result.packageName).isEqualTo(BuildConfig.APPLICATION_ID) }
    }

}
