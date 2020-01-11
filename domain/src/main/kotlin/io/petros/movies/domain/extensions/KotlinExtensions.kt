@file:Suppress("unused")

package io.petros.movies.domain.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/* SINGLE CHARACTER */

@Suppress("FunctionOnlyReturningConstant")
fun empty() = ""

@Suppress("FunctionOnlyReturningConstant")
fun space() = " "

@Suppress("FunctionOnlyReturningConstant")
fun leftParentheses() = "("

@Suppress("FunctionOnlyReturningConstant")
fun rightParentheses() = ")"

@Suppress("FunctionOnlyReturningConstant")
fun star() = "★"

@Suppress("FunctionOnlyReturningConstant")
fun colon() = ":"

/* GENERAL */

fun String.withParentheses() = leftParentheses() + this + rightParentheses()

fun Int.withParentheses() = leftParentheses() + this + rightParentheses()

/* DATE */

private const val SIMPLE_DATE_FORMAT_PATTERN = "MMMM"

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
    val monthDate = SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN, Locale.US)
    return monthDate.format(calendar.time)
}
