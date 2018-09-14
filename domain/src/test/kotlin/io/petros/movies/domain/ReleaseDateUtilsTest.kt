package io.petros.movies.domain

import io.petros.movies.domain.util.releaseDateGte
import io.petros.movies.domain.util.releaseDateLte
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_MONTH
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ReleaseDateUtilsTest {

    @Test
    fun `Given no year, when converting to release date, then null is returned`() {
        assertThat(releaseDateGte(null, MOVIE_MONTH)).isNull()
        assertThat(releaseDateLte(null, MOVIE_MONTH)).isNull()
    }

    @Test
    fun `When converting year to release date, then the expected range is returned`() {
        assertThat(releaseDateGte(MOVIE_YEAR, null)).isEqualTo("2018-01-01")
        assertThat(releaseDateLte(MOVIE_YEAR, null)).isEqualTo("2018-12-31")
    }

    @Test
    fun `When converting year and month to release date, then the expected range is returned`() {
        assertThat(releaseDateGte(MOVIE_YEAR, MOVIE_MONTH)).isEqualTo("2018-08-01")
        assertThat(releaseDateLte(MOVIE_YEAR, MOVIE_MONTH)).isEqualTo("2018-08-31")
    }

}
