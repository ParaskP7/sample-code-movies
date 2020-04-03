package io.petros.movies.network

import io.petros.movies.domain.model.MappableError
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import java.io.IOException

@Suppress("TooGenericExceptionCaught", "UseIfInsteadOfWhen")
inline fun <T : Any> withException(networkCall: () -> T) = try {
    networkCall()
} catch (e: Exception) {
    throw when (e) {
        is IOException -> NetworkException(e)
        else -> e
    }
}

data class NetworkException(override val cause: Exception) : Exception(), MappableError {
    override fun toError(): Result.Error = NetworkError(cause)
}
