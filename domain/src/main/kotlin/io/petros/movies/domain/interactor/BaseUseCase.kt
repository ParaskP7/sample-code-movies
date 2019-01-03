package io.petros.movies.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<Model, in Params, out Error : Throwable> {

    protected abstract suspend fun buildUseCase(params: Params): Model

    protected abstract suspend fun buildError(e: Exception): Error

    @Suppress("TooGenericExceptionCaught")
    suspend fun execute(params: Params) = withContext(Dispatchers.IO) {
        try {
            buildUseCase(params)
        } catch (e: Exception) {
            throw buildError(e)
        }
    }

}
