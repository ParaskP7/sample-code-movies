package io.petros.movies.movies.data.repository.settings

import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.android_test.runner.CustomRobolectricTestRunner
import io.petros.movies.data.repository.settings.SettingsRepositoryImpl
import io.petros.movies.datastore.MoviesDatastore
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import strikt.api.expect
import strikt.assertions.isEqualTo

@RunWith(CustomRobolectricTestRunner::class)
class SettingsRepositoryImplRobolectricTest {

    private val datastore = MoviesDatastore(context())
    private val testedClass = SettingsRepositoryImpl(datastore)

    @Test
    fun `given is compose is disabled, when compose gets enabled, then compose is enabled`() = runBlocking {
        val enabled = true
        expect { that(testedClass.isComposeEnabled()).isEqualTo(!enabled) }

        testedClass.setComposeEnabled(enabled)
        val result = testedClass.isComposeEnabled()

        expect { that(result).isEqualTo(enabled) }
    }

}
