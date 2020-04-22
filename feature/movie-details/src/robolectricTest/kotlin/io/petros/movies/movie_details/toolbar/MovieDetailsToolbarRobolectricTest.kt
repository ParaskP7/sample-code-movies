package io.petros.movies.movie_details.toolbar

import io.petros.movies.android_test.app.TestApp
import io.petros.movies.android_test.context.TestContextProvider.context
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import strikt.api.expect
import strikt.assertions.isA

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class MovieDetailsToolbarRobolectricTest {

    @Test
    fun `when movies toolbar is instantiated, then assert its instance (placeholder test)`() {
        expect { that(MovieDetailsToolbar(context())).isA<MovieDetailsToolbar>() }
    }

}
