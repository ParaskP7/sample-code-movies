package io.petros.movies.domain.model

interface MappableError {

    fun toError(): Result.Error

}

@Suppress("UseIfInsteadOfWhen")
fun Exception.toError(): Result.Error {
    return when (this) {
        is MappableError -> toError()
        else -> UnknownError(this)
    }
}

data class NetworkError(override val cause: Exception) : Result.Error(cause)
data class UnknownError(override val cause: Exception) : Result.Error(cause)
