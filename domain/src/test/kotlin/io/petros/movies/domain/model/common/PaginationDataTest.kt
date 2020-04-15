package io.petros.movies.domain.model.common

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.test.domain.movie
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isNull
import strikt.assertions.isTrue

class PaginationDataTest {

    companion object {

        private const val SECOND_PAGE = 2

    }

    private val firstPageItems = listOf(movie(id = 1), movie(id = 2), movie(id = 3))
    private val secondPageItems = listOf(movie(id = 4), movie(id = 5), movie(id = 6))

    private val testedClass = PaginationData<Movie>()

    @Test
    fun `given no pages, when checking if it is empty, then the return value is true`() {
        expect { that(testedClass.isEmpty()).isTrue() }
    }

    @Test
    fun `given no pages, when checking if it is first page, then the return value is false`() {
        expect { that(testedClass.isFirstPage()).isFalse() }
    }

    @Test
    fun `given no pages, when adding a page, then the items are the expected one`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))

        expect { that(testedClass.items()).isEqualTo(firstPageItems) }
    }

    @Test
    fun `given no pages, when adding a page, then the latest items are the expected one`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))

        expect { that(testedClass.latestItems()).isEqualTo(firstPageItems) }
    }

    @Test
    fun `given no pages, when adding a page, then the next page is the expected one`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))

        expect { that(testedClass.nextPage()).isEqualTo(SECOND_PAGE) }
    }

    @Test
    fun `given a single page, when checking if it is empty, then the return value is false`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))

        expect { that(testedClass.isEmpty()).isFalse() }
    }

    @Test
    fun `given a single page, when checking if it is the first page, then the return value is true`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))

        expect { that(testedClass.isFirstPage()).isTrue() }
    }

    @Test
    fun `given a single page, when adding a page, then the items are the expected one`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))
        testedClass.addPage(MoviesPage(SECOND_PAGE + 1, secondPageItems))

        expect { that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems) }
    }

    @Test
    fun `given a single page, when adding a page, then the latest items are the expected one`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))
        testedClass.addPage(MoviesPage(SECOND_PAGE + 1, secondPageItems))

        expect { that(testedClass.latestItems()).isEqualTo(secondPageItems) }
    }

    @Test
    fun `given a single page, when adding a page, then the next page is the expected one`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))
        testedClass.addPage(MoviesPage(SECOND_PAGE + 1, secondPageItems))

        expect { that(testedClass.nextPage()).isEqualTo(SECOND_PAGE + 1) }
    }

    @Test
    fun `given a multi page, when checking it is the first page, then the return value is false`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))
        testedClass.addPage(MoviesPage(SECOND_PAGE, secondPageItems))

        expect { that(testedClass.isFirstPage()).isFalse() }
    }

    @Test
    fun `given a multi page, when clearing the pages, then data gets emptied`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))
        testedClass.addPage(MoviesPage(SECOND_PAGE, secondPageItems))

        testedClass.clear()

        expect { that(testedClass.isEmpty()).isTrue() }
    }

    @Test
    fun `given a multi page, when clearing the pages, then items are empty`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))
        testedClass.addPage(MoviesPage(SECOND_PAGE, secondPageItems))

        testedClass.clear()

        expect { that(testedClass.items()).isEmpty() }
    }

    @Test
    fun `given a multi page, when clearing the pages, then latest items are null`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))
        testedClass.addPage(MoviesPage(SECOND_PAGE, secondPageItems))

        testedClass.clear()

        expect { that(testedClass.latestItems()).isNull() }
    }

    @Test
    fun `given a multi page, when clearing the pages, then next page is null`() {
        testedClass.addPage(MoviesPage(SECOND_PAGE, firstPageItems))
        testedClass.addPage(MoviesPage(SECOND_PAGE, secondPageItems))

        testedClass.clear()

        expect { that(testedClass.nextPage()).isNull() }
    }

}
