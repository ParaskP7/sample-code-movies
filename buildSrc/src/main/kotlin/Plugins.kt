@file:Suppress("InvalidPackageDeclaration")

object Plugins {

    object Version {

        // Releases: https://blog.jetbrains.com/kotlin/category/releases
        // Release: https://github.com/JetBrains/kotlin/releases/tag/v1.6.10
        private const val KOTLIN_1610 = "1.6.10" // Released: 14-12-21

        // Release: https://github.com/JetBrains/kotlin/releases/tag/v1.7.0
        @Suppress("unused") private const val KOTLIN = "1.7.0" // Released: 09-06-22
        const val KOTLIN_JETPACK_COMPOSE = KOTLIN_1610 // Released: 14-12-21

        // Releases: https://androidstudio.googleblog.com
        // Release: https://androidstudio.googleblog.com/2022/05/android-studio-chipmunk-available-in.html
        @Suppress("unused") const val ANDROID_GRADLE = "7.2.0" // Released: 09-05-22

        // Release: https://androidstudio.googleblog.com/2022/05/android-studio-dolphin-beta-1-now.html
        @Suppress("unused") const val ANDROID_GRADLE_BETA = "7.3.0-beta01" // Released: 11-05-22

        // Release: https://androidstudio.googleblog.com/2022/05/android-studio-electric-eel-canary-2.html
        const val ANDROID_GRADLE_CANARY = "7.4.0-alpha02" // Released: 13-05-22

        // Releases: https://github.com/GradleUp/auto-manifest/releases
        const val ANDROID_MANIFEST = "2.0" // Released: 16-04-22

        // Releases: https://github.com/detekt/detekt/releases
        const val DETEKT = "1.20.0" // Released: 15-04-22

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
