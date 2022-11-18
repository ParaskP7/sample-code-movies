package io.petros.movies

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.util.*

internal fun configureDependencyVersions(
    dependencyUpdatesTask: DependencyUpdatesTask,
) {
    dependencyUpdatesTask.apply {
        rejectVersionIf()
        gradleReleaseChannel = DependencyVersions.GRADLE_RELEASE_CHANNEL
        checkConstraints = false
        checkForGradleUpdate = true
        outputFormatter = DependencyVersions.OUTPUT_FORMATTER
        outputDir = DependencyVersions.OUTPUT_DIR
        reportfileName = DependencyVersions.REPORT_FILE_NAME
    }
}

/* REJECT VERSION IF */

fun DependencyUpdatesTask.rejectVersionIf() {
    rejectVersionIf {
        isDev(candidate.version) ||
                isAlpha(candidate.version) ||
                isMilestone(candidate.version)
    }
}

fun isDev(version: String) =
    DependencyVersions.devKeyword.any { version.toLowerCase(Locale.ROOT).contains(it) }

fun isAlpha(version: String) =
    DependencyVersions.alphaKeyword.any { version.toLowerCase(Locale.ROOT).contains(it) }

fun isMilestone(version: String) =
    DependencyVersions.milestoneKeyword.any { version.toUpperCase(Locale.ROOT).contains(it) }

/* CONFIG */

object DependencyVersions {

    const val GRADLE_RELEASE_CHANNEL = "release-candidate"
    const val OUTPUT_FORMATTER = "plain,json,xml,html"
    const val OUTPUT_DIR = "build/reports/dependency-updates"
    const val REPORT_FILE_NAME = "advice"

    val devKeyword = listOf(
        "dev",
    )
    val alphaKeyword = listOf(
        "alpha",
    )
    val milestoneKeyword = listOf(
        "M1",
        "M2",
    )

}
