@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Versions

object Plugins {

    object Version {

        // Releases: https://blog.jetbrains.com/kotlin/category/releases
        @Suppress("unused") const val KOTLIN = "1.3.72" // Released: 14-04-20
        const val KOTLIN_EAP = "1.4-M3" // Released: 06-07-20

        // Releases: https://androidstudio.googleblog.com
        @Suppress("unused") const val ANDROID = "4.0.0" // Released: 28-05-20
        @Suppress("unused") const val ANDROID_BETA = "4.1.0-beta03" // Released: 07-07-20
        const val ANDROID_CANARY = "4.2.0-alpha04" // Released: 08-07-20

        // Releases: https://github.com/GradleUp/auto-manifest/releases
        const val ANDROID_MANIFEST = "1.0.2" // Released: 24-05-20

        // Releases: https://github.com/detekt/detekt/releases
        const val DETEKT = "1.10.0" // Released: 27-06-20

        // Releases: https://github.com/mannodermaus/android-junit5/releases (not working with 'dependencyUpdates')
        const val ANDROID_J_UNIT_5 = "1.6.2.0" // Released: 03-05-20

        // Releases: https://github.com/ben-manes/gradle-versions-plugin/releases
        const val DEPENDENCY_VERSIONS = "0.28.0" // Released: 22-02-20

        // Releases: https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin/releases
        const val DEPENDENCY_ANALYSIS = "0.49.0" // Released: 15-06-20

        // Releases: https://github.com/runningcode/gradle-doctor/releases
        const val GRADLE_DOCTOR = "0.3.4" // Released: 20-04-20

        // Releases: https://github.com/jraska/modules-graph-assert/releases
        const val MODULE_GRAPH_ASSERT = "1.3.1" // Released: 12-05-20

    }

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN_EAP}"
    const val ANDROID = "com.android.tools.build:gradle:${Version.ANDROID_CANARY}"
    const val ANDROID_NAVIGATION =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Android.Arch.Navigation.NAVIGATION}"
    const val ANDROID_MANIFEST = "com.gradleup:auto-manifest-plugin:${Version.ANDROID_MANIFEST}"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Version.DETEKT}"
    const val DETEKT_FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:${Version.DETEKT}"
    const val ANDROID_J_UNIT_5 = "de.mannodermaus.gradle.plugins:android-junit5:${Version.ANDROID_J_UNIT_5}"
    const val DEPENDENCY_VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${Version.DEPENDENCY_VERSIONS}"
    const val DEPENDENCY_ANALYSIS = "com.autonomousapps:dependency-analysis-gradle-plugin:${Version.DEPENDENCY_ANALYSIS}"
    const val GRADLE_DOCTOR = "com.osacky.doctor:doctor-plugin:${Version.GRADLE_DOCTOR}"

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
