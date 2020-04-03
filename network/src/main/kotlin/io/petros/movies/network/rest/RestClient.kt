package io.petros.movies.network.rest

import io.petros.movies.network.WebService
import io.petros.movies.network.withException
import io.petros.movies.utils.releaseDateGte
import io.petros.movies.utils.releaseDateLte

class RestClient(
    private val restApi: RestApi
) : WebService {

    override suspend fun loadMovies(year: Int?, month: Int?, page: Int?) = withException {
        restApi.loadMovies(
            releaseDateGte(year, month),
            releaseDateLte(year, month),
            page
        ).toMoviesResultPage()
    }

}
