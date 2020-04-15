package io.petros.movies.utils

@Suppress("MagicNumber")
enum class MonthOfYear(
    val number: Int,
    val label: String
) {

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
    UNKNOWN_MONTH(-1, "Month");

    @Suppress("RemoveRedundantQualifierName")
    companion object {

        fun from(number: Int) = MonthOfYear.values().firstOrNull { it.number == number } ?: UNKNOWN_MONTH

        fun from(label: CharSequence) = MonthOfYear.values().firstOrNull { it.label == label } ?: UNKNOWN_MONTH

    }

}
