@file:Suppress("unused", "TooManyFunctions")

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

/* GENERAL */

fun String.withParentheses() = leftParentheses() + this + rightParentheses()

fun Int.withParentheses() = leftParentheses() + this + rightParentheses()

/* TIME */

private const val ONE_SEC_IN_MILLS: Long = 1_000L

fun toMillis(seconds: Int) = seconds * ONE_SEC_IN_MILLS

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

/* TRICKS */

/**
 * The superpower of sealed classes is only unleashed if when is used as an expression. Otherwise, the compiler doesn’t
 * force developers to handle all cases. However, with this little exhaustive trick, a when statement can be enforce to
 * become exhaustive.
 */
val <T> T.exhaustive: T
    get() = this
