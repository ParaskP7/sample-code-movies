package io.petros.movies.network.rest

import io.petros.movies.network.raw.movie.MoviesResultPageRaw
import retrofit2.http.GET
import retrofit2.http.Query

@Suppress("LongParameterList")
interface RestApi {

    @GET("3/discover/movie")
    suspend fun loadMovies(
        @Query("primary_release_date.gte") releaseDateGte: String? = null,
        @Query("primary_release_date.lte") releaseDateLte: String? = null,
        @Query("page") page: Int? = null,
        @Query("sort_by") sortBy: String = "popularity.desc",
        // TODO: Think about how to add this `BuildConfig.THEMOVIEDB_API_KEY` logic back
        @Query("api_key") clientId: String = "f075136f8e2f4871f75b4aa0e399ce49"
    ): MoviesResultPageRaw

}
