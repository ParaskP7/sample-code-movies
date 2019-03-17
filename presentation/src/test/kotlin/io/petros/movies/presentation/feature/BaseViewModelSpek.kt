package io.petros.movies.presentation.feature

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isTrue

class BaseViewModelSpek : Spek({

    Feature("Base view model") {
        val testedClass = BaseViewModel()
        Scenario("On cleared") {
            When("On cleared is triggered") {
                testedClass.onCleared()
            }
            Then("the job is cancelled") {
                expect { that(testedClass.job.isCancelled).isTrue() }
            }
        }
    }

})
