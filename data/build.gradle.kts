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

dependencies {
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Kotlin.NETWORK))
    implementation(project(Projects.Implementation.Android.Core.DATASTORE))
    implementation(project(Projects.Implementation.Android.Core.DATABASE))

    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.CORE_JVM)
    implementation(Deps.Android.Arch.DataStore.CORE)
    implementation(Deps.Android.Arch.DataStore.Preferences.CORE)
    implementation(Deps.Android.Arch.Database.Room.RUNTIME)
    implementation(Deps.Android.Arch.Database.Room.KTX)
    implementation(Deps.Android.Arch.Pagination.COMMON)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Di.Koin.Kotlin.CORE_JVM)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))
    testImplementation(project(Projects.TestImplementation.Android.ANDROID_TEST))

    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.Assert.STRIKT) { exclude(Deps.Test.Assert.Exclude.KOTLIN) }
    testImplementation(Deps.Test.Mock.MOCK_K)
    testImplementation(Deps.Test.Mock.DSL_JVM)
    testImplementation(Deps.Android.Test.Robolectric.ROBOLECTRIC)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                Deps.Android.Test.Robolectric.ROBOLECTRIC.identifier(), // Ignore remove advise. Required for tests.
            )
        }
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Projects.Implementation.Kotlin.NETWORK, // Ignore change to 'api' advice.
                Projects.Implementation.Android.Core.DATASTORE, // Ignore change to 'api' advice.
                Projects.Implementation.Android.Core.DATABASE, // Ignore change to 'api' advice.
                Deps.Android.Arch.Pagination.COMMON.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE_JVM.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
