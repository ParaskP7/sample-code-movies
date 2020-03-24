package io.petros.movies.domain.model.movie

import io.petros.movies.utils.space
import io.petros.movies.utils.star
import io.petros.movies.utils.toMonth
import io.petros.movies.utils.toYear
import io.petros.movies.utils.withParentheses
import java.io.Serializable
import java.util.*

@Suppress("DataClassContainsFunctions", "SerialVersionUIDInSerializableClass")
data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: Date,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String,
    val backdrop: String?
) : Serializable {

    fun releaseDate() = releaseDate.toYear().toString() + space() + releaseDate.toMonth().withParentheses()

    fun vote() = voteAverage.toString() + space() + star() + space() + voteCount.withParentheses()

}
