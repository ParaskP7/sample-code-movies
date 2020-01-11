package io.petros.movies.domain.model.common

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.domain.provideMovie
import org.junit.Before
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import strikt.assertions.isTrue

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
        expect { that(testedClass.isEmpty()).isTrue() }
    }

    @Test
    fun `Given some pages, when checking if it is empty, then the return value is false`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))

        expect { that(testedClass.isEmpty()).isFalse() }
    }

    @Test
    fun `Given single page, when checking if it is the first page, then the return value is true`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))

        expect { that(testedClass.isFirstPage()).isTrue() }
    }

    @Test
    fun `Given multiple pages, when checking it is the first page, then the return value is false`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, secondPageItems))

        expect { that(testedClass.isFirstPage()).isFalse() }
    }

    @Test
    fun `When adding a first page, then the pagination state is the expected one`() {
        expect {
            that(testedClass.items()).isEmpty()
            that(testedClass.latestItems()).isNull()
            that(testedClass.nextPage()).isNull()
        }
        val firstPage = MoviesResultPage(NEXT_PAGE, firstPageItems)

        testedClass.addPage(firstPage)

        expect {
            that(testedClass.items()).isEqualTo(firstPageItems)
            that(testedClass.latestItems()).isEqualTo(firstPageItems)
            that(testedClass.nextPage()).isEqualTo(NEXT_PAGE)
        }
    }

    @Test
    fun `When adding a second page, then the pagination state is the expected one`() {
        expect {
            that(testedClass.items()).isEmpty()
            that(testedClass.latestItems()).isNull()
            that(testedClass.nextPage()).isNull()
        }
        val firstPage = MoviesResultPage(NEXT_PAGE, firstPageItems)
        testedClass.addPage(firstPage)
        val secondPage = MoviesResultPage(NEXT_PAGE + 1, secondPageItems)

        testedClass.addPage(secondPage)

        expect {
            that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems)
            that(testedClass.latestItems()).isEqualTo(secondPageItems)
            that(testedClass.nextPage()).isEqualTo(NEXT_PAGE + 1)
        }
    }

    @Test
    fun `When pagination data is cleared, then all fields are reset`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        expect {
            that(testedClass.isEmpty()).isFalse()
            that(testedClass.latestItems()).isNotNull()
            that(testedClass.nextPage()).isNotNull()
        }

        testedClass.clear()

        expect {
            that(testedClass.isEmpty()).isTrue()
            that(testedClass.latestItems()).isNull()
            that(testedClass.nextPage()).isNull()
        }
    }

}
