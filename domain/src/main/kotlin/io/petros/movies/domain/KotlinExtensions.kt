package io.petros.movies.domain

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/* GENERAL */

fun space() = " "

fun leftParentheses() = "("

fun rightParentheses() = ")"

fun String.withParentheses(): String {
    return leftParentheses() + this + rightParentheses()
}

fun Int.withParentheses(): String {
    return leftParentheses() + this + rightParentheses()
}

fun star() = "★"

/* DATE */

@Suppress("SwallowedException")
fun String.toDate(dateFormat: String): Date {
    try {
        return SimpleDateFormat(dateFormat, Locale.US).parse(this)
    } catch (pe: ParseException) {
        throw ParseException("Cannot parse date. [Date Format: $dateFormat]", pe.errorOffset)
    }
}

fun Date.toYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.toMonth(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val monthDate = SimpleDateFormat("MMMM", Locale.US)
    return monthDate.format(calendar.time)
}
