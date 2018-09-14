package io.petros.movies.domain.util

import java.text.SimpleDateFormat
import java.util.*

const val MOVIE_DATE_FORMAT = "yyyy-MM-dd"

fun releaseDateGte(year: Int?, month: Int?): String? {
    val releaseDateGte = when (year) {
        null -> null
        else -> when (month) {
            null -> {
                val calendar = Calendar.getInstance()
                calendar.set(year, 0, 1)
                calendar.time
            }
            else -> {
                val calendar = Calendar.getInstance()
                calendar.set(year, month, 1)
                calendar.time
            }
        }
    }
    return releaseDateGte?.let { SimpleDateFormat(MOVIE_DATE_FORMAT, Locale.US).format(it) }
}

fun releaseDateLte(year: Int?, month: Int?): String? {
    val releaseDateLte = when (year) {
        null -> null
        else -> when (month) {
            null -> {
                val calendar = Calendar.getInstance()
                calendar.set(year, 0, 1)
                calendar.add(Calendar.YEAR, 1)
                calendar.add(Calendar.DAY_OF_YEAR, -1)
                calendar.time
            }
            else -> {
                val calendar = Calendar.getInstance()
                calendar.set(year, month, 1)
                calendar.add(Calendar.MONTH, 1)
                calendar.add(Calendar.DAY_OF_MONTH, -1)
                calendar.time
            }
        }
    }
    return releaseDateLte?.let { SimpleDateFormat(MOVIE_DATE_FORMAT, Locale.US).format(it) }
}
