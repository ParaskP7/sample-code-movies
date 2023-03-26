package io.petros.movies

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeaturesCompose()
        composeOptions(project)
    }
}

/* BUILD FEATURES */

@Suppress("UnstableApiUsage")
fun CommonExtension<*, *, *, *, *>.buildFeaturesCompose() {
    buildFeatures {
        compose = true
    }
}

/* COMPOSE OPTIONS */

fun CommonExtension<*, *, *, *, *>.composeOptions(project: Project) {
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named(Versions.Extension.LIBS)
    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion(Versions.ANDROIDX_COMPOSE_COMPILER).get().toString()
    }
}
