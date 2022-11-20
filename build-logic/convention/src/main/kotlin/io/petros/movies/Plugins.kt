package io.petros.movies

object Plugins {

    object Id {

        @Suppress("MemberNameEqualsClassName")
        object Kotlin {

            const val KOTLIN = "kotlin"
            const val KSP = "com.google.devtools.ksp"
            const val ANDROID = "org.jetbrains.kotlin.android"

        }

        object Android {

            const val APPLICATION = "com.android.application"
            const val LIBRARY = "com.android.library"

        }

        object Quality {

            const val DETEKT = "io.gitlab.arturbosch.detekt"

        }

        object Test {

            const val JACOCO = "org.gradle.jacoco"

        }

        object Dependency {

            const val VERSIONS = "com.github.ben-manes.versions"
            const val ANALYSIS = "com.autonomousapps.dependency-analysis"

        }

        object Gradle {

            const val DOCTOR = "com.osacky.doctor"

        }

        object Module {

            const val GRAPH_ASSERT = "com.jraska.module.graph.assertion"

        }

    }

}
