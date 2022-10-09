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
    namespace = App.APPLICATION_ID + Projects.Implementation.Android.Core.DATASTORE.namespace()
}

dependencies {
    implementation(Deps.Kotlin.Coroutines.CORE_JVM)
    implementation(Deps.Android.Arch.DataStore.CORE)
    implementation(Deps.Android.Arch.DataStore.Preferences.CORE)
    implementation(Deps.Android.Arch.DataStore.Preferences.PREFERENCES)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Di.Koin.Kotlin.CORE_JVM)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Deps.Android.Arch.DataStore.CORE.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.DataStore.Preferences.CORE.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE_JVM.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
