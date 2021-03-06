package io.petros.movies.config.tests

object Tests {

    object Engine {

        @Suppress("MemberNameEqualsClassName")
        object Spek {

            const val SPEK = "spek2"

        }

        object JUnit {

            const val VINTAGE = "junit-vintage" // For JUnit 4 based tests.

            @Suppress("unused")
            const val JUPITER = "junit-jupiter" // For JUnit 5 based tests.

        }

    }

}
