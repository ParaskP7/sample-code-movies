package io.petros.movies.data.network.rest

import android.content.Context
import io.petros.movies.data.network.WebService
import io.petros.movies.data.network.rest.mapper.movie.MoviesMapper
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.domain.util.releaseDateGte
import io.petros.movies.domain.util.releaseDateLte
import io.reactivex.Single
import javax.inject.Inject

class RestClient @Inject constructor(
    private val context: Context,
    private val restApi: RestApi
) : WebService {

    override fun loadMovies(year: Int?, month: Int?, page: Int?): Single<MoviesResultPage> {
        return restApi.loadMovies(
            releaseDateGte(year, month),
            releaseDateLte(year, month),
            page
        ).map { MoviesMapper.transform(context, it) }
    }

}
