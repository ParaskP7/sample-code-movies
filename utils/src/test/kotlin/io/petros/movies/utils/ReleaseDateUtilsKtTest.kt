package io.petros.movies.utils

import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

class ReleaseDateUtilsKtTest {

    companion object {

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

    @Test
    fun `given no year, when converting (greater than or equal), then null is returned`() {
        expect { that(releaseDateGte(null, MOVIE_MONTH)).isNull() }
    }

    @Test
    fun `given no year, when converting (less than or equal), then null is returned`() {
        expect { that(releaseDateLte(null, MOVIE_MONTH)).isNull() }
    }

    @Test
    fun `given year but no month, when converting year (greater than or equal), then expected range is returned`() {
        expect { that(releaseDateGte(MOVIE_YEAR, null)).isEqualTo("2018-01-01") }
    }

    @Test
    fun `given year but no month, when converting year (less than or equal), then expected range is returned`() {
        expect { that(releaseDateLte(MOVIE_YEAR, null)).isEqualTo("2018-12-31") }
    }

    @Test
    fun `given year and month, When converting year and month (greater than or equal), then expected range is returned`() {
        expect { that(releaseDateGte(MOVIE_YEAR, MOVIE_MONTH)).isEqualTo("2018-08-01") }
    }

    @Test
    fun `given year and month, When converting year and month (less than or equal), then expected range is returned`() {
        expect { that(releaseDateLte(MOVIE_YEAR, MOVIE_MONTH)).isEqualTo("2018-08-31") }
    }

}
