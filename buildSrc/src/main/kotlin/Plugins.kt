@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Versions

object Plugins {

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugin.KOTLIN}"
    const val ANDROID = "com.android.tools.build:gradle:${Versions.Plugin.ANDROID}"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.Plugin.DETEKT}"
    const val ANDROID_J_UNIT_5 = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.Plugin.ANDROID_J_UNIT_5}"
    const val VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${Versions.Plugin.VERSIONS}"

    object Id {

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

}
