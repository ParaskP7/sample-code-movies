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
    implementation(project(Projects.Implementation.Kotlin.UTILS))
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Android.Core.ANDROID_UTILS))
    implementation(project(Projects.Implementation.Android.Core.CORE))
    implementation(project(Projects.Implementation.Android.Lib.PICKER))

    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.CORE_JVM)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.Core.FRAGMENT)
    implementation(Deps.Android.Core.RECYCLER_VIEW)
    implementation(Deps.Android.Core.CONSTRAINT_LAYOUT)
    implementation(Deps.Android.Core.COORDINATOR_LAYOUT)
    implementation(Deps.Android.Ktx.CORE)
    implementation(Deps.Android.Arch.Lifecycle.COMMON)
    implementation(Deps.Android.Arch.Lifecycle.RUNTIME_KTX)
    implementation(Deps.Android.Arch.Lifecycle.LIVE_DATA_CORE)
    implementation(Deps.Android.Arch.Lifecycle.VIEW_MODEL)
    implementation(Deps.Android.Arch.Lifecycle.VIEW_MODEL_KTX)
    implementation(Deps.Android.Arch.Navigation.COMMON)
    implementation(Deps.Android.Arch.Navigation.COMMON_KTX)
    implementation(Deps.Android.Arch.Navigation.RUNTIME)
    implementation(Deps.Android.Arch.Navigation.FRAGMENT_KTX)
    implementation(Deps.Android.Arch.Pagination.COMMON)
    implementation(Deps.Android.Arch.Pagination.RUNTIME)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Di.Koin.Kotlin.CORE_JVM)
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))
    testImplementation(project(Projects.TestImplementation.Android.ANDROID_TEST))

    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.Assert.STRIKT) { exclude(Deps.Test.Assert.Exclude.KOTLIN) }
    testImplementation(Deps.Test.Mock.MOCK_K)
    testImplementation(Deps.Test.Mock.DSL_JVM)
    testImplementation(Deps.Android.Arch.Core.TESTING)
    testImplementation(Deps.Android.Test.Robolectric.ROBOLECTRIC)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                Deps.Android.Ktx.CORE.identifier(), // Ignore remove advise. Required for 'is(In)Visible'.
                Deps.Android.Test.Robolectric.ROBOLECTRIC.identifier(), // Ignore remove advise. Required for tests.
            )
        }
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Projects.Implementation.Android.Core.CORE, // Ignore change to 'api' advice.
                Deps.Material.MATERIAL.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Core.CONSTRAINT_LAYOUT.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Core.COORDINATOR_LAYOUT.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Core.RECYCLER_VIEW.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.Pagination.COMMON.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.Pagination.RUNTIME.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE_JVM.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
