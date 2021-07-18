@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Projects
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
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Android.Core.CORE_COMPOSE))

    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.CORE_JVM)
    implementation(Deps.Android.Compose.Runtime.RUNTIME)
    implementation(Deps.Android.Compose.UI.UI)
    implementation(Deps.Android.Compose.UI.GRAPHICS)
    implementation(Deps.Android.Compose.UI.TEXT)
    implementation(Deps.Android.Compose.UI.UNIT)
    implementation(Deps.Android.Compose.UI.TOOLING)
    implementation(Deps.Android.Compose.Foundation.FOUNDATION)
    implementation(Deps.Android.Compose.Foundation.LAYOUT)
    implementation(Deps.Android.Compose.Material.MATERIAL)
    implementation(Deps.Android.Compose.ConstraintLayout.CONSTRAINT_LAYOUT)
    implementation(Deps.Image.Coil.COMPOSE)
    implementation(Deps.Android.Core.FRAGMENT)
    implementation(Deps.Android.Arch.Lifecycle.VIEW_MODEL)
    implementation(Deps.Android.Arch.Lifecycle.VIEW_MODEL_KTX)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Di.Koin.Kotlin.CORE_JVM)
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Log.TIMBER)

    implementation(project(Projects.TestImplementation.Kotlin.TEST))

    detektPlugins(Plugins.DETEKT_FORMATTING)
}
