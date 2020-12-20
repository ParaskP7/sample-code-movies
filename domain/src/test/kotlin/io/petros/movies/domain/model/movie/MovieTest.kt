package io.petros.movies.domain.model.movie

import io.petros.movies.test.domain.movie
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

class MovieTest {

    @Test
    fun `given null release date, when release date string is constructed, then it is the expected one`() {
        expect { that(movie().copy(releaseDate = null).releaseDate()).isEqualTo("Not Available") }
    }

    @Test
    fun `given non null release date, when release date string is constructed, then it is the expected one`() {
        expect { that(movie().releaseDate()).isEqualTo("2019 (September)") }
    }

    @Test
    fun `when vote string is constructed, then it is the expected one`() {
        expect { that(movie().vote()).isEqualTo("6.0 ★ (2958)") }
    }

}
