package io.petros.movies.domain.utils

import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

class ReleaseDateUtilsTest {

    @Test
    fun `Given no year, when converting to release date (greater than or equal), then null is returned`() {
        expect { that(releaseDateGte(null, MOVIE_MONTH)).isNull() }
    }

    @Test
    fun `Given no year, when converting to release date (less than or equal), then null is returned`() {
        expect { that(releaseDateLte(null, MOVIE_MONTH)).isNull() }
    }

    @Test
    fun `Given year but no month, when converting year to release date (greater than or equal), then the expected range is returned`() {
        expect { that(releaseDateGte(MOVIE_YEAR, null)).isEqualTo("2018-01-01") }
    }

    @Test
    fun `Given year but no month, when converting year to release date (less than or equal), then the expected range is returned`() {
        expect { that(releaseDateLte(MOVIE_YEAR, null)).isEqualTo("2018-12-31") }
    }

    @Test
    fun `Given year and month, When converting year and month to release date (greater than or equal), then the expected range is returned`() {
        expect { that(releaseDateGte(MOVIE_YEAR, MOVIE_MONTH)).isEqualTo("2018-08-01") }
    }

    @Test
    fun `Given year and month, When converting year and month to release date (less than or equal), then the expected range is returned`() {
        expect { that(releaseDateLte(MOVIE_YEAR, MOVIE_MONTH)).isEqualTo("2018-08-31") }
    }

}
