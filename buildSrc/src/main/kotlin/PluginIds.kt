@file:Suppress("InvalidPackageDeclaration")

object PluginIds {

    @Suppress("MemberNameEqualsClassName")
    object Kotlin {

        object Android {

            const val ANDROID = "kotlin-android"

        }

        const val KOTLIN = "kotlin"
        const val KAPT = "kotlin-kapt"

    }

    object Android {

        const val APPLICATION = "com.android.application"
        const val LIBRARY = "com.android.library"

    }

    object Quality {

        const val DETEKT = "io.gitlab.arturbosch.detekt"

    }

    object Test {

        object Android {

            const val J_UNIT_5 = "de.mannodermaus.android-junit5"

        }

        const val JACOCO = "jacoco"
        const val COVERAGE = "io.petros.movies.coverage"

    }

    object Dependency {

        const val VERSIONS = "com.github.ben-manes.versions"

    }

}
