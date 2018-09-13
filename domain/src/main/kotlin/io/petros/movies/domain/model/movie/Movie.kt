package io.petros.movies.domain.model.movie

import io.petros.movies.domain.space
import io.petros.movies.domain.star
import io.petros.movies.domain.toMonth
import io.petros.movies.domain.toYear
import io.petros.movies.domain.withParentheses
import java.io.Serializable
import java.util.*

data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: Date,
    val voteAverage: Double,
    val voteCount: Int,
    val backdrop: String
) : Serializable {

    fun releaseDate() = releaseDate.toYear().toString() + space() + releaseDate.toMonth().withParentheses()

    fun vote() = voteAverage.toString() + space() + star() + space() + voteCount.withParentheses()

}
