package io.petros.movies.domain.model.movie

import io.petros.movies.test.domain.movie
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

class MovieTest {

    private val testedClass = movie()

    @Test
    fun `when release date string is constructed, then it is the expected one`() {
        expect { that(testedClass.releaseDate()).isEqualTo("2018 (September)") }
    }

    @Test
    fun `when vote string is constructed, then it is the expected one`() {
        expect { that(testedClass.vote()).isEqualTo("10.0 ★ (100)") }
    }

}
