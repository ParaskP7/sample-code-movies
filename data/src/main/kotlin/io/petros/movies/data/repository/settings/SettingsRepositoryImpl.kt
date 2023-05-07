@file:Suppress("all")

package io.petros.movies.data.repository.settings

//import androidx.datastore.preferences.core.edit
//import io.petros.movies.datastore.MoviesDatastore
import io.petros.movies.domain.repository.settings.SettingsRepository
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.flow.map

// TODO: Revert this!
class SettingsRepositoryImpl(
//    private val datastore: MoviesDatastore,
) : SettingsRepository {

//    override suspend fun isComposeEnabled() = datastore.settings().data
//        .map { it[datastore.isComposeEnabled()] == true }
//        .first()
    override suspend fun isComposeEnabled() = false

//    override suspend fun setComposeEnabled(isEnabled: Boolean) {
//        datastore.settings().edit {
//            it[datastore.isComposeEnabled()] = isEnabled
//        }
//    }
    override suspend fun setComposeEnabled(isEnabled: Boolean) = Unit

}
