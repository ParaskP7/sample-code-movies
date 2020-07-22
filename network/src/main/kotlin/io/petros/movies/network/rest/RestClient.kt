package io.petros.movies.network.rest

import io.petros.movies.network.MoviesService
import io.petros.movies.network.withException
import io.petros.movies.utils.releaseDateGte
import io.petros.movies.utils.releaseDateLte

class RestClient(
    private val restApi: RestApi,
) : MoviesService {

    override suspend fun loadMovies(year: Int?, month: Int?, page: Int?) = withException {
        restApi.loadMovies(
            releaseDateGte(year, month),
            releaseDateLte(year, month),
            page,
        ).toMoviesPage()
    }

    override suspend fun loadMovie(id: Int) = withException {
        restApi.loadMovie(
            id,
        ).toMovie()
    }

}
