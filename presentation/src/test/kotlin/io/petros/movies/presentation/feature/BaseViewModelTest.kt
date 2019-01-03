package io.petros.movies.presentation.feature

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class BaseViewModelTest {

    private lateinit var testedClass: BaseViewModel

    @Before
    fun setUp() {
        testedClass = BaseViewModel()
    }

    @Test
    fun `When base view model is destroyed, then the job is cancelled`() {
        assertThat(testedClass.job.isCancelled).isFalse()

        testedClass.onCleared()

        assertThat(testedClass.job.isCancelled).isTrue()
    }

}
