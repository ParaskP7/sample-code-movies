package io.petros.movies.domain.model.common

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.domain.movie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isNull
import strikt.assertions.isTrue

object PaginationDataSpek : Spek({

    val firstPageItems = listOf(movie(id = 1), movie(id = 2), movie(id = 3))
    val secondPageItems = listOf(movie(id = 4), movie(id = 5), movie(id = 6))

    Feature("Pagination date with no pages") {
        val testedClass by memoized { PaginationData<Movie>() }
        Scenario("is empty") {
            var result = false
            When("checking if it is empty") {
                result = testedClass.isEmpty()
            }
            Then("the return value is true") {
                expect { that(result).isTrue() }
            }
        }
        Scenario("add page") {
            When("adding a page") {
                testedClass.addPage(MoviesPage(NEXT_PAGE, firstPageItems))
            }
            Then("the items are the expected one") {
                expect { that(testedClass.items()).isEqualTo(firstPageItems) }
            }
            Then("the latest items are the expected one") {
                expect { that(testedClass.latestItems()).isEqualTo(firstPageItems) }
            }
            Then("the next page is the expected one") {
                expect { that(testedClass.nextPage()).isEqualTo(NEXT_PAGE) }
            }
        }
    }

    Feature("Pagination date with a single page") {
        val testedClass by memoized { PaginationData<Movie>() }
        Scenario("is empty") {
            Given("a single paged data") {
                testedClass.addPage(MoviesPage(NEXT_PAGE, firstPageItems))
            }
            var result = false
            When("checking if it is empty") {
                result = testedClass.isEmpty()
            }
            Then("the return value is false") {
                expect { that(result).isFalse() }
            }
        }
        Scenario("is first page") {
            var result = false
            Given("a single page") {
                testedClass.addPage(MoviesPage(NEXT_PAGE, firstPageItems))
            }
            When("checking if it is the first page") {
                result = testedClass.isFirstPage()
            }
            Then("the return value is true") {
                expect { that(result).isTrue() }
            }
        }
        Scenario("add page") {
            Given("a single page") {
                testedClass.addPage(MoviesPage(NEXT_PAGE, firstPageItems))
            }
            When("adding a page") {
                testedClass.addPage(MoviesPage(NEXT_PAGE + 1, secondPageItems))
            }
            Then("the items are the expected one") {
                expect { that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems) }
            }
            Then("the latest items are the expected one") {
                expect { that(testedClass.latestItems()).isEqualTo(secondPageItems) }
            }
            Then("the next page is the expected one") {
                expect { that(testedClass.nextPage()).isEqualTo(NEXT_PAGE + 1) }
            }
        }
    }

    Feature("Pagination data with multiple pages") {
        val testedClass by memoized { PaginationData<Movie>() }
        Scenario("is first page") {
            Given("a multi page") {
                testedClass.addPage(MoviesPage(NEXT_PAGE, firstPageItems))
                testedClass.addPage(MoviesPage(NEXT_PAGE + 1, secondPageItems))
            }
            var result = false
            When("checking if it is the first page") {
                result = testedClass.isFirstPage()
            }
            Then("the return value is false") {
                expect { that(result).isFalse() }
            }
        }
        Scenario("clear") {
            Given("a multi page") {
                testedClass.addPage(MoviesPage(NEXT_PAGE, firstPageItems))
                testedClass.addPage(MoviesPage(NEXT_PAGE + 1, secondPageItems))
            }
            When("clearing the pages") {
                testedClass.clear()
            }
            Then("the data gets emptied") {
                expect { that(testedClass.isEmpty()).isTrue() }
            }
            Then("the items are empty") {
                expect { that(testedClass.items()).isEmpty() }
            }
            Then("the latest items are null") {
                expect { that(testedClass.latestItems()).isNull() }
            }
            Then("the next page is null") {
                expect { that(testedClass.nextPage()).isNull() }
            }
        }
    }

})
