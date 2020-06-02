@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Versions

object Plugins {

    object Version {

        // Releases: https://blog.jetbrains.com/kotlin/category/releases
        const val KOTLIN = "1.3.72" // Released: 14-04-20

        // Releases: https://androidstudio.googleblog.com
        const val ANDROID = "4.1.0-alpha10" // Released: 28-05-20

        // Releases: https://github.com/GradleUp/auto-manifest/releases
        const val ANDROID_MANIFEST = "1.0.2" // Released: 24-05-20

        // Releases: https://github.com/detekt/detekt/releases
        const val DETEKT = "1.9.1" // Released: 17-05-20

        // Releases: https://github.com/mannodermaus/android-junit5/releases (not working with 'dependencyUpdates')
        const val ANDROID_J_UNIT_5 = "1.6.2.0" // Released: 03-05-20

        // Releases: https://github.com/ben-manes/gradle-versions-plugin/releases
        const val VERSIONS = "0.28.0" // Released: 22-02-20

    }

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val ANDROID = "com.android.tools.build:gradle:${Version.ANDROID}"
    const val ANDROID_NAVIGATION =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Android.Arch.Navigation.EXTENSIONS}"
    const val ANDROID_MANIFEST = "com.gradleup:auto-manifest-plugin:${Version.ANDROID_MANIFEST}"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Version.DETEKT}"
    const val DETEKT_FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:${Version.DETEKT}"
    const val ANDROID_J_UNIT_5 = "de.mannodermaus.gradle.plugins:android-junit5:${Version.ANDROID_J_UNIT_5}"
    const val VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${Version.VERSIONS}"

    object Id {

        @Suppress("MemberNameEqualsClassName")
        object Kotlin {

            object Android {

                const val ANDROID = "kotlin-android"
                @Suppress("unused") const val ANDROID_NAVIGATION = "androidx.navigation.safeargs.kotlin"
                const val ANDROID_MANIFEST = "com.gradleup.auto.manifest"

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
