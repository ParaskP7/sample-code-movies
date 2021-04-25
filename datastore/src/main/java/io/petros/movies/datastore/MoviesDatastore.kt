package io.petros.movies.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val SETTINGS = "settings"

private val Context.settings by preferencesDataStore(SETTINGS)

@Suppress("SyntheticAccessor")
class MoviesDatastore(
    private val context: Context,
) {

    fun settings() = context.settings

    fun isComposeEnabled() = booleanPreferencesKey(IS_COMPOSE_ENABLED_KEY)

    companion object {

        private const val IS_COMPOSE_ENABLED_KEY = "is_compose_enabled"

    }

}
