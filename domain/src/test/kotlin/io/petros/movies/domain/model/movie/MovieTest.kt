package io.petros.movies.domain.model.movie

import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class MovieTest {

    private lateinit var testedClass: Movie

    @Before
    fun setUp() {
        testedClass = provideMovie()
    }

    @Test
    fun `When release date string is constructed, then it is the expected one`() {
        assertThat(testedClass.releaseDate()).isEqualTo("2018 (September)")
    }

    @Test
    fun `When vote string is constructed, then it is the expected one`() {
        assertThat(testedClass.vote()).isEqualTo("10.0 ★ (100)")
    }

}
