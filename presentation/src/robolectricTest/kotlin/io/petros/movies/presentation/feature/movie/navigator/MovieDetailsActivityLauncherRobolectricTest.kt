package io.petros.movies.presentation.feature.movie.navigator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.presentation.PreconfiguredRobolectricTestRunner
import io.petros.movies.presentation.feature.movie.MovieDetailsActivity
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor

@RunWith(PreconfiguredRobolectricTestRunner::class)
class MovieDetailsActivityLauncherRobolectricTest {

    companion object {

        private const val EXTRA_MOVIE = "movie"

    }

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
        assertThat(intentCaptor.value?.component?.className).isEqualTo(MovieDetailsActivity::class.java.name)
        assertThat(intentCaptor.value?.extras?.get(EXTRA_MOVIE)).isEqualTo(movie)
    }

}
