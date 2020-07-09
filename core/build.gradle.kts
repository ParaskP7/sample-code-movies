@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Projects
import io.petros.movies.config.deps.identifier

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Test.JACOCO)
    id(Plugins.Id.Test.COVERAGE)
}

android.buildFeatures {
    viewBinding = true
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Android.Core.ANDROID_UTILS))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Core.KOTLIN_REFLECT) // Added due to 'stateful' dependency.
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.FRAGMENT)
    implementation(Deps.Android.Core.RECYCLER_VIEW)
    implementation(Deps.Android.Arch.Lifecycle.COMMON)
    implementation(Deps.Android.Arch.Lifecycle.COMMON_JAVA_8)
    implementation(Deps.Android.Arch.Lifecycle.LIVE_DATA_CORE)
    implementation(Deps.Android.Arch.Lifecycle.VIEW_MODEL)
    implementation(Deps.Architecture.Mvi.STATEFUL) { exclude(Deps.Architecture.Mvi.Exclude.KOTLIN) }
    implementation(Deps.Image.Glide.GLIDE)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))
    testImplementation(project(Projects.TestImplementation.Android.ANDROID_TEST))

    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testImplementation(Deps.Test.Assert.STRIKT) { exclude(Deps.Test.Assert.Exclude.KOTLIN) }
    testImplementation(Deps.Test.Mock.MOCK_K)
    testImplementation(Deps.Android.Test.Robolectric.ROBOLECTRIC)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Projects.Implementation.Android.Core.ANDROID_UTILS, // Ignore change to 'api' advice.
                Deps.Kotlin.Core.KOTLIN.identifier(), // Ignore change to 'api' advice.
                Deps.Material.MATERIAL.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Core.APP_COMPAT.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Core.FRAGMENT.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Core.RECYCLER_VIEW.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.Lifecycle.LIVE_DATA_CORE.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.Lifecycle.VIEW_MODEL.identifier(), // Ignore change to 'api' advice.
                Deps.Architecture.Mvi.STATEFUL.identifier() // Ignore change to 'api' advice.
            )
        }
    }
}
