package io.petros.movies.domain.util

import java.text.SimpleDateFormat
import java.util.*

const val MOVIE_DATE_FORMAT = "yyyy-MM-dd"

fun releaseDateGte(year: Int?, month: Int?): String? {
    val releaseDateGte = when (year) {
        null -> null
        else -> releaseDateGte(year, month)
    }
    return releaseDateGte?.let { SimpleDateFormat(MOVIE_DATE_FORMAT, Locale.US).format(it) }
}

private fun releaseDateGte(year: Int, month: Int?): Date {
    return when (month) {
        null -> releaseDateGte(year)
        else -> releaseDateGte(year, month)
    }
}

private fun releaseDateGte(year: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, 0, 1)
    return calendar.time
}

private fun releaseDateGte(year: Int, month: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)
    return calendar.time
}

fun releaseDateLte(year: Int?, month: Int?): String? {
    val releaseDateLte = when (year) {
        null -> null
        else -> releaseDateLte(year, month)
    }
    return releaseDateLte?.let { SimpleDateFormat(MOVIE_DATE_FORMAT, Locale.US).format(it) }
}

private fun releaseDateLte(year: Int, month: Int?): Date {
    return when (month) {
        null -> releaseDateLte(year)
        else -> releaseDateLte(year, month)
    }
}

private fun releaseDateLte(year: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, 0, 1)
    calendar.add(Calendar.YEAR, 1)
    calendar.add(Calendar.DAY_OF_YEAR, -1)
    return calendar.time
}

private fun releaseDateLte(year: Int, month: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)
    calendar.add(Calendar.MONTH, 1)
    calendar.add(Calendar.DAY_OF_MONTH, -1)
    return calendar.time
}
