package io.petros.movies.presentation.feature

import org.junit.Before
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isFalse
import strikt.assertions.isTrue

class BaseViewModelTest {

    private lateinit var testedClass: BaseViewModel

    @Before
    fun setUp() {
        testedClass = BaseViewModel()
    }

    @Test
    fun `When base view model is destroyed, then the job is cancelled`() {
        expect { that(testedClass.job.isCancelled).isFalse() }

        testedClass.onCleared()

        expect { that(testedClass.job.isCancelled).isTrue() }
    }

}
