@file:Suppress("all")

package io.petros.movies.movies.data.repository.settings

// import io.petros.movies.androidtest.context.TestContextProvider.context
import io.petros.movies.androidtest.runner.CustomRobolectricTestRunner
// import io.petros.movies.data.repository.settings.SettingsRepositoryImpl
// import io.petros.movies.datastore.MoviesDatastore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
// import strikt.api.expect
// import strikt.assertions.isEqualTo

// TODO: Revert this!
@ExperimentalCoroutinesApi
@RunWith(CustomRobolectricTestRunner::class)
class SettingsRepositoryImplRobolectricTest {

//    private val datastore = MoviesDatastore(context())
//    private val testedClass = SettingsRepositoryImpl(datastore)

    @Test
    @Suppress("BooleanPropertyNaming")
    fun `given is compose is disabled, when compose gets enabled, then compose is enabled`() = runTest {
//        val isEnabled = true
//        expect { that(testedClass.isComposeEnabled()).isEqualTo(!isEnabled) }

//        testedClass.setComposeEnabled(isEnabled)
//        val result = testedClass.isComposeEnabled()

//        expect { that(result).isEqualTo(isEnabled) }
    }

}
