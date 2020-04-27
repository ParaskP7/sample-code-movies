package io.petros.movies.network.rest

import io.petros.movies.network.raw.movie.MoviesPageRaw
import retrofit2.http.GET
import retrofit2.http.Query

@Suppress("LongParameterList")
interface RestApi {

    @GET("3/discover/movie")
    @Suppress("ForbiddenComment")
    suspend fun loadMovies(
        @Query("primary_release_date.gte") releaseDateGte: String? = null,
        @Query("primary_release_date.lte") releaseDateLte: String? = null,
        @Query("page") page: Int? = null,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("api_key") clientId: String = THEMOVIEDB_API_KEY
    ): MoviesPageRaw

}
