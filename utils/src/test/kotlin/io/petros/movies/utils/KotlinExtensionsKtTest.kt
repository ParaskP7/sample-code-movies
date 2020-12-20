package io.petros.movies.utils

import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNull
import java.text.ParseException
import java.util.*

class KotlinExtensionsKtTest {

    /* SINGLE CHARACTER */

    @Test
    fun `when empty is triggered, then an empty string is returned`() {
        expect { that(empty()).isEqualTo("") }
    }

    @Test
    fun `when space is triggered, then a space string is returned`() {
        expect { that(space()).isEqualTo(" ") }
    }

    @Test
    fun `when left parentheses is triggered, then a left parenthesis string is returned`() {
        expect { that(leftParentheses()).isEqualTo("(") }
    }

    @Test
    fun `when right parentheses is triggered, then a right parenthesis string is returned`() {
        expect { that(rightParentheses()).isEqualTo(")") }
    }

    @Test
    fun `when start is triggered, then a star string is returned`() {
        expect { that(star()).isEqualTo("★") }
    }

    @Test
    fun `when colon is triggered, then a colon string is returned`() {
        expect { that(colon()).isEqualTo(":") }
    }

    @Test
    fun `when slash is triggered, then a slash string is returned`() {
        expect { that(slash()).isEqualTo("/") }
    }

    @Test
    fun `given string, when with parentheses is triggered, then the string is enclosed in parentheses`() {
        val string = "word"

        val result = string.withParentheses()

        expect { that(result).isEqualTo("($string)") }
    }

    /* PARENTHESES */

    @Test
    fun `given integer, when with parentheses is triggered, then the integer is enclosed in parentheses`() {
        val integer = 1

        val result = integer.withParentheses()

        expect { that(result).isEqualTo("($integer)") }
    }

    /* DATE */

    @Test
    fun `given empty date as string, when to date is triggered, then a null date is returned`() {
        val date = ""

        val result = date.toDate(MOVIE_DATE_FORMAT)

        expect { that(result).isNull() }
    }

    @Test
    fun `given valid date as string, when to date is triggered, then the expect date is returned`() {
        val date = "2019-09-17"

        val result = date.toDate(MOVIE_DATE_FORMAT)

        expect { that(result).isEqualTo(GregorianCalendar(2019, Calendar.SEPTEMBER, 17).time) }
    }

    @Test(expected = ParseException::class)
    fun `given invalid date as string, when to date is triggered, then throw parse exception`() {
        val date = "09-17"

        date.toDate(MOVIE_DATE_FORMAT)
    }

    @Test
    fun `when to date is triggered, then the expect date is returned`() {
        val date = GregorianCalendar(2019, Calendar.SEPTEMBER, 17).time

        val result = date.toDate(MOVIE_DATE_FORMAT)

        expect { that(result).isEqualTo("2019-09-17") }
    }

    @Test
    fun `when to year is triggered, then the expect year is returned`() {
        val year = 2019
        val date = GregorianCalendar(year, Calendar.SEPTEMBER, 17).time

        val result = date.toYear()

        expect { that(result).isEqualTo(year) }
    }

    @Test
    fun `when to month is triggered, then the expect month is returned`() {
        val month = MonthOfYear.SEPTEMBER
        val date = GregorianCalendar(2019, month.number, 17).time

        val result = date.toMonth()

        expect { that(result).isEqualTo(month.label) }
    }

}
