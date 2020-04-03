package io.petros.movies.domain.model

sealed class Result<out T : Any> {

    data class Success<T : Any>(
        val value: T
    ) : Result<T>()

    abstract class Error(
        open val cause: Exception
    ) : Result<Nothing>()

}

@Suppress("TooGenericExceptionCaught")
inline fun <T : Any> asResult(block: () -> T) = try {
    Result.Success(block())
} catch (e: Exception) {
    e.toError()
}
