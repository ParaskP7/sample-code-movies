package io.petros.movies.data.network.rest

import android.content.Context
import io.petros.movies.data.network.WebService
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.domain.util.releaseDateGte
import io.petros.movies.domain.util.releaseDateLte

class RestClient constructor(
    private val context: Context,
    private val restApi: RestApi
) : WebService {

    override suspend fun loadMovies(year: Int?, month: Int?, page: Int?): MoviesResultPage {
        return restApi.loadMovies(
            releaseDateGte(year, month),
            releaseDateLte(year, month),
            page
        ).toMoviesResultPage(context)
    }

}
