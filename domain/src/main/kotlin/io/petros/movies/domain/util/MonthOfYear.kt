package io.petros.movies.domain.util

@Suppress("MagicNumber")
enum class MonthOfYear(
    val month: Int?,
    val monthName: String
) { // MUT

    JANUARY(0, "January"),
    FEBRUARY(1, "February"),
    MARCH(2, "March"),
    APRIL(3, "April"),
    MAY(4, "May"),
    JUNE(5, "June"),
    JULY(6, "July"),
    AUGUST(7, "August"),
    SEPTEMBER(8, "September"),
    OCTOBER(9, "October"),
    NOVEMBER(10, "November"),
    DECEMBER(11, "December"),
    UNKNOWN_MONTH(null, "Month");

    companion object {

        fun from(month: Int) = MonthOfYear.values().firstOrNull { it.month == month } ?: UNKNOWN_MONTH

        fun from(monthName: CharSequence) = MonthOfYear.values().firstOrNull { it.monthName == monthName } ?: UNKNOWN_MONTH

    }

}
