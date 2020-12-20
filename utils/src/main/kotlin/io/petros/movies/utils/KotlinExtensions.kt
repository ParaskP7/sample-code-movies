@file:Suppress("TooManyFunctions")

package io.petros.movies.utils

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

@Suppress("FunctionOnlyReturningConstant")
fun slash() = "/"

/* PARENTHESES */

fun String.withParentheses() = leftParentheses() + this + rightParentheses()

fun Int.withParentheses() = leftParentheses() + this + rightParentheses()

/* DATE */

private const val SIMPLE_DATE_FORMAT_PATTERN = "MMMM"

@Suppress("SwallowedException")
fun String.toDate(dateFormat: String): Date? {
    try {
        return if (!isEmpty()) SimpleDateFormat(dateFormat, Locale.US).parse(this) else null
    } catch (pe: ParseException) {
        throw ParseException("Cannot parse date. [Date Format: $dateFormat]", pe.errorOffset)
    }
}

fun Date.toDate(dateFormat: String): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val date = SimpleDateFormat(dateFormat, Locale.US)
    return date.format(calendar.time)
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
