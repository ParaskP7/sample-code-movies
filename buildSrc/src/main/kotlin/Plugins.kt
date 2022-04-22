@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Versions

object Plugins {

    object Version {

        // Releases: https://blog.jetbrains.com/kotlin/category/releases
        // Release: https://github.com/JetBrains/kotlin/releases/tag/v1.6.10
        private const val KOTLIN_1610 = "1.6.10" // Released: 14-12-21

        // Release: https://github.com/JetBrains/kotlin/releases/tag/v1.6.20
        @Suppress("unused") private const val KOTLIN = "1.6.21" // Released: 20-04-22
        const val KOTLIN_JETPACK_COMPOSE = KOTLIN_1610 // Released: 14-12-21

        // Releases: https://androidstudio.googleblog.com
        // Release: https://androidstudio.googleblog.com/2021/06/android-studio-422-available.html
        @Suppress("unused") const val ANDROID_GRADLE_OLD = "4.2.2" // Released: 30-06-21

        // Release: https://androidstudio.googleblog.com/2021/12/android-studio-arctic-fox-202031-patch.html
        @Suppress("unused") const val ANDROID_GRADLE_NEW = "7.0.4" // Released: 08-12-21

        // Release: https://androidstudio.googleblog.com/2022/04/android-studio-bumblebee-202111-patch-3.html
        @Suppress("unused") const val ANDROID_GRADLE = "7.1.3" // Released: 07-04-22

        // Release: https://androidstudio.googleblog.com/2022/04/android-studio-chipmunk-rc-1-now.html
        @Suppress("unused") const val ANDROID_GRADLE_BETA = "7.2.0-rc01" // Released: 20-04-22

        // Release: https://androidstudio.googleblog.com/2022/04/android-studio-dolphin-canary-9-now.html
        const val ANDROID_GRADLE_CANARY = "7.3.0-alpha09" // Released: 22-04-22

        // Releases: https://github.com/GradleUp/auto-manifest/releases
        const val ANDROID_MANIFEST = "2.0" // Released: 16-04-22

        // Releases: https://github.com/detekt/detekt/releases
        const val DETEKT = "1.19.0" // Released: 30-11-21
        @Suppress("unused") const val DETEKT_RC = "1.20.0-RC1" // Released: 28-02-22

        // Releases: https://github.com/ben-manes/gradle-versions-plugin/releases
        const val DEPENDENCY_VERSIONS = "0.42.0" // Released: 04-02-22

        // Releases: https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin/releases
        const val DEPENDENCY_ANALYSIS = "1.1.0" // Released: 15-04-22

        // Releases: https://github.com/runningcode/gradle-doctor/releases
        const val GRADLE_DOCTOR = "0.8.0" // Released: 15-02-22

        // Releases: https://github.com/jraska/modules-graph-assert/releases
        const val MODULE_GRAPH_ASSERT = "2.2.0" // Released: 18-01-22

        // Releases: https://github.com/jacoco/jacoco/releases
        const val JACOCO = "0.8.8" // Released: 05-04-22

    }

    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN_JETPACK_COMPOSE}"
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

        }

        object Module {

            const val GRAPH_ASSERT = "com.jraska.module.graph.assertion"

        }

    }

}
