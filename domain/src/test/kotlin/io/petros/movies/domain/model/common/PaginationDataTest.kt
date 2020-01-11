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
    fun `Given no pages, when adding a page, then the items are the expected one`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))

        expect { that(testedClass.items()).isEqualTo(firstPageItems) }
    }

    @Test
    fun `Given no pages, when adding a page, then the latest items are the expected one`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))

        expect { that(testedClass.latestItems()).isEqualTo(firstPageItems) }
    }

    @Test
    fun `Given no pages, when adding a page, then the next page is the expected one`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))

        expect { that(testedClass.nextPage()).isEqualTo(NEXT_PAGE) }
    }

    @Test
    fun `Given a single page, when checking if it is empty, then the return value is false`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))

        expect { that(testedClass.isEmpty()).isFalse() }
    }

    @Test
    fun `Given a single page, when checking if it is the first page, then the return value is true`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))

        expect { that(testedClass.isFirstPage()).isTrue() }
    }

    @Test
    fun `Given a single page, when adding a page, then the items are the expected one`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE + 1, secondPageItems))

        expect { that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems) }
    }

    @Test
    fun `Given a single page, when adding a page, then the latest items are the expected one`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE + 1, secondPageItems))

        expect { that(testedClass.latestItems()).isEqualTo(secondPageItems) }
    }

    @Test
    fun `Given a single page, when adding a page, then the next page is the expected one`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE + 1, secondPageItems))

        expect { that(testedClass.nextPage()).isEqualTo(NEXT_PAGE + 1) }
    }

    @Test
    fun `Given a multi page, when checking it is the first page, then the return value is false`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, secondPageItems))

        expect { that(testedClass.isFirstPage()).isFalse() }
    }

    @Test
    fun `Given a multi page, when clearing the pages, then data gets emptied`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, secondPageItems))

        testedClass.clear()

        expect { that(testedClass.isEmpty()).isTrue() }
    }

    @Test
    fun `Given a multi page, when clearing the pages, then items are empty`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, secondPageItems))

        testedClass.clear()

        expect { that(testedClass.items()).isEmpty() }
    }

    @Test
    fun `Given a multi page, when clearing the pages, then latest items are null`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, secondPageItems))

        testedClass.clear()

        expect { that(testedClass.latestItems()).isNull() }
    }

    @Test
    fun `Given a multi page, when clearing the pages, then next page is null`() {
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.addPage(MoviesResultPage(NEXT_PAGE, secondPageItems))

        testedClass.clear()

        expect { that(testedClass.nextPage()).isNull() }
    }

}
