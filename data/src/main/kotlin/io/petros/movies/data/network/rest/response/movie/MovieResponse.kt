package io.petros.movies.data.network.rest.response.movie

data class MovieResponse(
    val vote_count: Int,
    val id: Int,
    val vote_average: Double,
    val title: String,
    val backdrop_path: String,
    val overview: String,
    val release_date: String
)
