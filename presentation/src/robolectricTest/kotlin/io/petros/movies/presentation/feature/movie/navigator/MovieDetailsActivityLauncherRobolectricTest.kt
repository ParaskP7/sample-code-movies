package io.petros.movies.presentation.feature.movie.navigator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.petros.movies.presentation.feature.movie.MovieDetailsActivity
import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsActivityLauncher.Companion.getMovie
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.robolectric.RobolectricTestRunner
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@RunWith(RobolectricTestRunner::class)
class MovieDetailsActivityLauncherRobolectricTest {

    private val movie = provideMovie()

    private val intentCaptor = ArgumentCaptor.forClass(Intent::class.java)

    private lateinit var testedClass: MovieDetailsActivityLauncher
    private var appCompatActivityMock = mock<AppCompatActivity>()

    @Before
    fun setUp() {
        testedClass = MovieDetailsActivityLauncher(appCompatActivityMock)
    }

    @Test
    fun `When launch is called, then current activity starts target movies activity`() {
        testedClass.launch(movie)

        verify(appCompatActivityMock).startActivity(intentCaptor.capture())
        expectThat(intentCaptor.value?.component?.className).isEqualTo(MovieDetailsActivity::class.java.name)
        expectThat(intentCaptor.value?.let { getMovie(it) }).isEqualTo(movie)
    }

}
