package io.petros.movies.utils

import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

class MonthOfYearTest {

    @Test
    fun `given number zero, when getting month of year from number, then the expect enum is returned`() {
        expect { that(MonthOfYear.from(0)).isEqualTo(MonthOfYear.JANUARY) }
    }

    @Test
    fun `given number eleven, when getting month of year from number, then the expect enum is returned`() {
        expect { that(MonthOfYear.from(11)).isEqualTo(MonthOfYear.DECEMBER) }
    }

    @Test
    fun `given unknown number, when getting month of year from number, then an unknown enum is returned`() {
        expect { that(MonthOfYear.from(99)).isEqualTo(MonthOfYear.UNKNOWN_MONTH) }
    }

    @Test
    fun `given january label, when getting month of year from label, then the expect enum is returned`() {
        expect { that(MonthOfYear.from("January")).isEqualTo(MonthOfYear.JANUARY) }
    }

    @Test
    fun `given december label, when getting month of year from label, then the expect enum is returned`() {
        expect { that(MonthOfYear.from("December")).isEqualTo(MonthOfYear.DECEMBER) }
    }

    @Test
    fun `given unknown label, when getting month of year from label, then an unknown enum is returned`() {
        expect { that(MonthOfYear.from("Un-december")).isEqualTo(MonthOfYear.UNKNOWN_MONTH) }
    }

}
