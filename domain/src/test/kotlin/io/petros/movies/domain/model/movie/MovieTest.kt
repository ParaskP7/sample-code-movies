package io.petros.movies.domain.model.movie

import io.petros.movies.test.domain.provideMovie
import org.junit.Before
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

class MovieTest {

    private lateinit var testedClass: Movie

    @Before
    fun setUp() {
        testedClass = provideMovie()
    }

    @Test
    fun `When release date string is constructed, then it is the expected one`() {
        expect { that(testedClass.releaseDate()).isEqualTo("2018 (September)") }
    }

    @Test
    fun `When vote string is constructed, then it is the expected one`() {
        expect { that(testedClass.vote()).isEqualTo("10.0 ★ (100)") }
    }

}
