package io.petros.movies.domain.repository.settings

interface SettingsRepository {

    suspend fun isComposeEnabled(): Boolean

    suspend fun setComposeEnabled(isEnabled: Boolean)

}
