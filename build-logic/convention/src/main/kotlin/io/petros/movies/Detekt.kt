package io.petros.movies

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureDetekt(
    detektExtension: DetektExtension,
) {
    detektExtension.apply {
        toolVersion(project)
        parallel = false
        config(project)
        buildUponDefaultConfig = false
        disableDefaultRuleSets = false
        debug = false
        ignoreFailures = false
        reports()
        autoCorrect = true
    }
    detektPlugins()
}

/* TOOL VERSION */

fun DetektExtension.toolVersion(project: Project) {
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named(Versions.Extension.LIBS)
    toolVersion = libs.findVersion(Versions.DETEKT).get().toString()
}

/* CONFIG */

fun DetektExtension.config(project: Project) {
    config = project.files("${project.rootDir}/${Detekt.CONFIG_FILE_PATH}")
}

/* REPORTS */

@Suppress("DEPRECATION")
fun DetektExtension.reports() {
    reports {
        html.enabled = true
        xml.enabled = true
        txt.enabled = true
    }
}

/* DETEKT PLUGINS */

fun Project.detektPlugins() {
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named(Versions.Extension.LIBS)
    dependencies {
        add(Versions.ConfigurationName.DETEKT_PLUGIN, libs.findLibrary(Versions.DETEKT_FORMATTING).get())
    }
}

/* CONFIG */

object Detekt {

    const val CONFIG_FILE_PATH = "${Config.QUALITY_DIRECTORY}/${Files.Yml.DETEKT}"

    object Files {

        object Yml {

            const val DETEKT = "detekt.yml"

        }

    }

}
