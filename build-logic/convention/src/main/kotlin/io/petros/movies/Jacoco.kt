package io.petros.movies

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

internal fun Project.configureJacoco(
    jacocoPluginExtension: JacocoPluginExtension,
) {
    jacocoPluginExtension.apply {
        toolVersion(project)
    }
    jacoco()
    robolectric()
}

/* TOOL VERSION */

fun JacocoPluginExtension.toolVersion(project: Project) {
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named(Versions.Extension.LIBS)
    toolVersion = libs.findVersion(Versions.JACOCO).get().toString()
}

/* JACOCO REPORT */

fun Project.jacoco() {
    val isKotlinModule = pluginManager.hasPlugin(Plugins.Id.Kotlin.KOTLIN)
    tasks.create(Jacoco.Build.Tasks.JACOCO, JacocoReport::class.java) { report(isKotlinModule) }
}

fun JacocoReport.report(isKotlinModule: Boolean) {
    group = Jacoco.GROUP
    description = Jacoco.DESCRIPTION
    reports {
        html.required.set(true)
        html.outputLocation.set(project.file(Jacoco.REPORT_HTML_DIRECTORY_PATH))
        xml.required.set(true)
        xml.outputLocation.set(project.file(Jacoco.REPORT_XML_FILE_PATH))
        csv.required.set(true)
        csv.outputLocation.set(project.file(Jacoco.REPORT_CSV_FILE_PATH))
    }
    sourceDirectories.setFrom(project.files(Sources.Main.KOTLIN))
    additionalSourceDirs.setFrom(project.files(Sources.Main.KOTLIN))
    classDirectories.setFrom(
        project.fileTree(Jacoco.ClassDirectories.dirPair(isKotlinModule)) {
            exclude(Jacoco.ClassDirectories.excludesPair(isKotlinModule))
        }
    )
    executionData(
        project.fileTree(Jacoco.ExecutionData.dirPair()) {
            include(Jacoco.ExecutionData.includePair(isKotlinModule))
        }
    )
}

/* ROBOLECTRIC */

private fun Project.robolectric() {
    tasks.withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            // Required for JaCoCo + Robolectric
            // https://github.com/robolectric/robolectric/issues/2230
            isIncludeNoLocationClasses = true
            // Required for JDK 11 with the above
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = Jacoco.Robolectric.excludes
        }
    }
}

/* CONFIG */

object Jacoco {

    private const val REPORT_DIRECTORY_PATH = "build/reports/jacoco"
    private const val REPORT_HTML_DIRECTORY = "jacocoHtml"
    private const val REPORT_XML_FILE = "jacoco.xml"
    private const val REPORT_CSV_FILE = "jacoco.csv"

    const val GROUP = "Reporting"
    const val DESCRIPTION = "Generate Jacoco code coverage reports."

    const val REPORT_HTML_DIRECTORY_PATH = "$REPORT_DIRECTORY_PATH/$REPORT_HTML_DIRECTORY"
    const val REPORT_XML_FILE_PATH = "$REPORT_DIRECTORY_PATH/$REPORT_XML_FILE"
    const val REPORT_CSV_FILE_PATH = "$REPORT_DIRECTORY_PATH/$REPORT_CSV_FILE"

    object Build {

        object Tasks {

            const val TEST = "test"
            const val JACOCO = "jacoco"

        }

    }

    object ClassDirectories {

        fun dirPair(isKotlinModule: Boolean): String {
            val kotlinDir = "build/classes/kotlin/main"
            val androidDir = "build/tmp/kotlin-classes/debug"
            return if (isKotlinModule) kotlinDir else androidDir
        }

        fun excludesPair(isKotlinModule: Boolean): List<String> {
            val kotlinExcludes = listOf(
                "**/*Module*.*",
            )
            val androidExcludes = listOf(
                "**/*Activity*.*",
                "**/*Fragment*.*",
                "**/*Mvi*.*",
                "**/*Module*.*",
                "**/*Binding*.*",
                "**/*Glide*.*",
            )
            return if (isKotlinModule) kotlinExcludes else androidExcludes
        }

    }

    object ExecutionData {

        @Suppress("FunctionOnlyReturningConstant")
        fun dirPair(): String {
            return "build"
        }

        fun includePair(isKotlinModule: Boolean): List<String> {
            val kotlinExecutionData = listOf(
                "jacoco/test.exec",
            )
            val androidExecutionData = listOf(
                "jacoco/testDebugUnitTest.exec",
            )
            return if (isKotlinModule) kotlinExecutionData else androidExecutionData
        }

    }

    object Robolectric {

        val excludes = listOf(
            "jdk.internal.*",
        )

    }

}
