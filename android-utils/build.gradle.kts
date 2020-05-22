@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.UTILS))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Android.Arch.Core.Lifecycle.LIVE_DATA)
    implementation(Deps.Log.TIMBER)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}
