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
    val releaseDate: Date?,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String,
    val backdrop: String?,
) : Serializable {

    fun releaseDate() = if (releaseDate != null) {
        releaseDate.toYear().toString() + space() + releaseDate.toMonth().withParentheses()
    } else {
        NOT_AVAILABLE
    }

    fun vote() = voteAverage.toString() + space() + star() + space() + voteCount.withParentheses()

    companion object {

        private const val NOT_AVAILABLE = "Not Available"

        val Default = Movie(
            0,
            "Title",
            GregorianCalendar(1, Calendar.JANUARY, 1).time,
            0.0,
            0,
            "Overview",
            null,
        )

    }

}
