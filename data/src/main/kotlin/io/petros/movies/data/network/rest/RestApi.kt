package io.petros.movies.data.network.rest

import io.petros.movies.data.BuildConfig
import io.petros.movies.data.network.rest.response.movie.MoviesResultPageRaw
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

@Suppress("LongParameterList")
interface RestApi {

    @GET("3/discover/movie")
    fun loadMovies(
        @Query("primary_release_date.gte") releaseDateGte: String? = null,
        @Query("primary_release_date.lte") releaseDateLte: String? = null,
        @Query("page") page: Int? = null,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("api_key") clientId: String = BuildConfig.THEMOVIEDB_API_KEY
    ): Deferred<MoviesResultPageRaw>

}
