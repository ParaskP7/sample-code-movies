package io.petros.movies

import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlin(
    kotlinBasePlugin: KotlinBasePlugin,
) {
    kotlinBasePlugin.apply {
        kotlinOptions()
        testOptions()
        sourceSets()
    }
}

/* KOTLIN OPTIONS */

fun Project.kotlinOptions() {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            kotlinOptions()
        }
    }
}

@Suppress("SuspiciousCollectionReassignment")
fun KotlinJvmOptions.kotlinOptions() {
    jvmTarget = Java.version.toString()
    allWarningsAsErrors = true
    freeCompilerArgs += Kotlin.Options.freeCompilerArgs
}

/* TEST OPTIONS */

fun Project.testOptions() {
    tasks.withType<Test> {
        testLogging()
    }
}

/* SOURCE SETS */

fun Project.sourceSets() {
    configure<SourceSetContainer> {
        named(Sources.MAIN) { mainSourceSets() }
        named(Sources.TEST) { testSourceSets() }
    }
}

fun SourceSet.mainSourceSets() {
    java.setSrcDirs(
        arrayListOf(
            Sources.Main.KOTLIN,
        )
    )
    resources.setSrcDirs(
        arrayListOf(
            Sources.Main.RESOURCES,
        )
    )
}

fun SourceSet.testSourceSets() {
    java.setSrcDirs(
        arrayListOf(
            Sources.Test.KOTLIN,
            Sources.Integration.KOTLIN,
        )
    )
    resources.setSrcDirs(
        arrayListOf(
            Sources.Test.RESOURCES,
            Sources.Integration.RESOURCES,
        )
    )
}

/* CONFIG */

object Kotlin {

    object Options {

        val freeCompilerArgs = listOf(
            "-opt-in=kotlin.RequiresOptIn",
        )

    }

}
