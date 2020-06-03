@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.identifier

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Kotlin.KAPT)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Test.Android.J_UNIT_5)
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Test.JACOCO)
    id(Plugins.Id.Test.COVERAGE)
}

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.UTILS))
    implementation(project(Deps.Project.Implementation.Kotlin.DOMAIN))
    implementation(project(Deps.Project.Implementation.Android.Core.ANDROID_UTILS))
    implementation(project(Deps.Project.Implementation.Android.Core.CORE))
    implementation(project(Deps.Project.Implementation.Android.Feature.PICKER))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.Core.FRAGMENT)
    implementation(Deps.Android.Core.RECYCLER_VIEW)
    implementation(Deps.Android.Core.CONSTRAINT_LAYOUT)
    implementation(Deps.Android.Core.COORDINATOR_LAYOUT)
    implementation(Deps.Android.Ktx.CORE)
    implementation(Deps.Android.Arch.Lifecycle.LIVE_DATA_CORE)
    implementation(Deps.Android.Arch.Lifecycle.VIEW_MODEL)
    implementation(Deps.Android.Arch.Lifecycle.VIEW_MODEL_KTX)
    implementation(Deps.Android.Arch.Navigation.COMMON)
    implementation(Deps.Android.Arch.Navigation.COMMON_KTX)
    implementation(Deps.Android.Arch.Navigation.RUNTIME)
    implementation(Deps.Android.Arch.Navigation.FRAGMENT_KTX)
    implementation(Deps.Architecture.Mvi.STATEFUL)
    kapt(Deps.Architecture.Mvi.STATEFUL_COMPILER)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Di.Koin.Android.VIEW_MODEL)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Deps.Project.TestImplementation.Kotlin.TEST))
    testImplementation(project(Deps.Project.TestImplementation.Android.ANDROID_TEST))

    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_5)
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT_5)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.Assert.STRIKT)
    testImplementation(Deps.Test.Mock.MOCK_K)
    testImplementation(Deps.Android.Arch.Core.TESTING)
    testImplementation(Deps.Android.Test.Robolectric.ROBOLECTRIC)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Deps.Project.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Deps.Project.Implementation.Android.Core.CORE, // Ignore change to 'api' advice.
                Deps.Material.MATERIAL.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Core.CONSTRAINT_LAYOUT.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Core.COORDINATOR_LAYOUT.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Core.RECYCLER_VIEW.identifier(), // Ignore change to 'api' advice.
                Deps.Architecture.Mvi.STATEFUL.identifier() // Ignore change to 'api' advice.
            )
        }
    }
}
