package io.petros.movies.domain.model.common

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class PaginationDataTest {

    private val firstPageItems = listOf(provideMovie(id = 1), provideMovie(id = 2), provideMovie(id = 3))
    private val secondPageItems = listOf(provideMovie(id = 4), provideMovie(id = 5), provideMovie(id = 6))

    private lateinit var testedClass: PaginationData<Movie>

    @Before
    fun setUp() {
        testedClass = PaginationData()
    }

    @Test
    fun `Given no pages, when checking if it is empty, then the return value is true`() {
        assertThat(testedClass.isEmpty()).isTrue()
    }

    @Test
    fun `Given some pages, when checking if it is empty, then the return value is false`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))

        assertThat(testedClass.isEmpty()).isFalse()
    }

    @Test
    fun `Given single page, when checking if it is the first page, then the return value is true`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))

        assertThat(testedClass.isFirstPage()).isTrue()
    }

    @Test
    fun `Given multiple pages, when checking it is the first page, then the return value is false`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, secondPageItems))

        assertThat(testedClass.isFirstPage()).isFalse()
    }

    @Test
    fun `When adding a first page, then the pagination state is the expected one`() {
        assertThat(testedClass.allPageItems).isEmpty()
        assertThat(testedClass.latestPage).isNull()
        assertThat(testedClass.nextPage).isNull()
        val firstPage = MoviesResultPage(NEXT_PAGE, firstPageItems)

        testedClass.addPage(firstPage)

        assertThat(testedClass.allPageItems).isEqualTo(firstPageItems)
        assertThat(testedClass.latestPage).isEqualTo(firstPage)
        assertThat(testedClass.nextPage).isEqualTo(NEXT_PAGE)
    }

    @Test
    fun `When adding a second page, then the pagination state is the expected one`() {
        assertThat(testedClass.allPageItems).isEmpty()
        assertThat(testedClass.latestPage).isNull()
        assertThat(testedClass.nextPage).isNull()
        val firstPage = MoviesResultPage(NEXT_PAGE, firstPageItems)
        testedClass.addPage(firstPage)
        val secondPage = MoviesResultPage(NEXT_PAGE + 1, secondPageItems)

        testedClass.addPage(secondPage)

        assertThat(testedClass.allPageItems).isEqualTo(firstPageItems + secondPageItems)
        assertThat(testedClass.latestPage).isEqualTo(secondPage)
        assertThat(testedClass.nextPage).isEqualTo(NEXT_PAGE + 1)
    }

    @Test
    fun `When pagination data is cleared, then all fields are reset`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        assertThat(testedClass.isEmpty()).isFalse()
        assertThat(testedClass.latestPage).isNotNull
        assertThat(testedClass.nextPage).isNotNull()

        testedClass.clear()

        assertThat(testedClass.isEmpty()).isTrue()
        assertThat(testedClass.latestPage).isNull()
        assertThat(testedClass.nextPage).isNull()
    }

}
