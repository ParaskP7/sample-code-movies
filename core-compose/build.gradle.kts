@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Versions

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Android.Compose.COMPOSE_RC
    }
}

dependencies {
    implementation(Deps.Android.Compose.Runtime.RUNTIME)
    implementation(Deps.Android.Compose.UI.GRAPHICS)
    implementation(Deps.Android.Compose.UI.TEXT)
    implementation(Deps.Android.Compose.UI.UNIT)
    implementation(Deps.Android.Compose.Foundation.FOUNDATION)
    implementation(Deps.Android.Compose.Material.MATERIAL)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}
