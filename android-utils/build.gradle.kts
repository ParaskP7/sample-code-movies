@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.android.App
import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Projects
import io.petros.movies.config.deps.identifier
import io.petros.movies.config.deps.namespace

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

android {
    namespace = App.APPLICATION_ID + Projects.Implementation.Android.Core.ANDROID_UTILS.namespace()
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.UTILS))

    implementation(Deps.Android.Core.FRAGMENT)
    implementation(Deps.Android.Arch.Lifecycle.COMMON)
    implementation(Deps.Android.Arch.Lifecycle.LIVE_DATA_CORE)
    implementation(Deps.Log.TIMBER)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Deps.Android.Core.FRAGMENT.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.Lifecycle.COMMON.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.Lifecycle.LIVE_DATA_CORE.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
