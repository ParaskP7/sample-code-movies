package io.petros.movies.data.network.rest

import io.petros.movies.data.BuildConfig
import io.petros.movies.data.network.rest.response.movie.MoviesResultPageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("3/discover/movie")
    fun loadMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("api_key") clientId: String = BuildConfig.THEMOVIEDB_API_KEY
    ): Single<MoviesResultPageResponse>

}
