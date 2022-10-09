@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.android.App
import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Projects
import io.petros.movies.config.deps.identifier
import io.petros.movies.config.deps.namespace

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Kotlin.KAPT)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

android {
    namespace = App.APPLICATION_ID + Projects.Implementation.Android.Core.DATABASE.namespace()
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.UTILS))
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))

    implementation(Deps.Kotlin.Coroutines.CORE_JVM)
    implementation(Deps.Android.Arch.Database.SQLight.SQLIGHT)
    implementation(Deps.Android.Arch.Database.Room.COMMON)
    implementation(Deps.Android.Arch.Database.Room.RUNTIME)
    implementation(Deps.Android.Arch.Database.Room.KTX)
    implementation(Deps.Android.Arch.Database.Room.PAGING)
    kapt(Deps.Android.Arch.Database.Room.COMPILER)
    implementation(Deps.Android.Arch.Paging.COMMON)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Di.Koin.Kotlin.CORE_JVM)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Deps.Kotlin.Coroutines.CORE_JVM.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.Database.Room.RUNTIME.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.Paging.COMMON.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE_JVM.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
