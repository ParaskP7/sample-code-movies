@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Versions

object Plugins {

    object Version {

        // Releases: https://blog.jetbrains.com/kotlin/category/releases
        const val KOTLIN = "1.4.32" // Released: 25-03-21

        // Releases: https://androidstudio.googleblog.com
        @Suppress("unused") const val ANDROID_GRADLE = "4.1.3" // Released: 18-03-21
        @Suppress("unused") const val ANDROID_GRADLE_BETA = "4.2.0-rc01" // Released: 07-04-21
        const val ANDROID_GRADLE_CANARY = "7.0.0-alpha15" // Released: 29-04-21

        // Releases: https://github.com/GradleUp/auto-manifest/releases
        const val ANDROID_MANIFEST = "1.0.4" // Released: 13-11-20

        // Releases: https://github.com/detekt/detekt/releases
        const val DETEKT = "1.17.0-RC1" // Released: 10-03-21

        // Releases: https://github.com/ben-manes/gradle-versions-plugin/releases
        const val DEPENDENCY_VERSIONS = "0.38.0" // Released: 04-02-21

        // Releases: https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin/releases
        const val DEPENDENCY_ANALYSIS = "0.72.0" // Released: 17-04-20

        // Releases: https://github.com/runningcode/gradle-doctor/releases
        const val GRADLE_DOCTOR = "0.7.0" // Released: 04-10-20

        // Releases: https://github.com/dipien/bye-bye-jetifier/releases
        const val GRADLE_JETIFIER = "1.1.2" // Released: 16-02-21

        // Releases: https://github.com/jraska/modules-graph-assert/releases
        const val MODULE_GRAPH_ASSERT = "1.4.0" // Released: 17-08-20

    }

    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Version.ANDROID_GRADLE_CANARY}"
    const val ANDROID_NAVIGATION =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Android.Arch.Navigation.NAVIGATION}"
    const val ANDROID_MANIFEST = "com.gradleup:auto-manifest-plugin:${Version.ANDROID_MANIFEST}"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Version.DETEKT}"
    const val DETEKT_FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:${Version.DETEKT}"
    const val DEPENDENCY_VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${Version.DEPENDENCY_VERSIONS}"
    const val DEPENDENCY_ANALYSIS =
        "com.autonomousapps:dependency-analysis-gradle-plugin:${Version.DEPENDENCY_ANALYSIS}"
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

            const val JACOCO = "jacoco"
            const val COVERAGE = "io.petros.movies.coverage"

        }

        object Dependency {

            const val VERSIONS = "com.github.ben-manes.versions"
            const val ANALYSIS = "com.autonomousapps.dependency-analysis"

        }

        object Gradle {

            const val DOCTOR = "com.osacky.doctor"
            const val JETIFIER = "com.dipien.byebyejetifier"

        }

        object Module {

            const val GRAPH_ASSERT = "com.jraska.module.graph.assertion"

        }

    }

}
