@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Dependency.VERSIONS)
}

dependencies {
    implementation(project(Deps.Project.Implementation.Android.Core.CORE))
}
