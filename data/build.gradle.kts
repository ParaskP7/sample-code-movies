@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Projects
import io.petros.movies.config.deps.identifier

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Test.Android.J_UNIT_5)
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Test.JACOCO)
    id(Plugins.Id.Test.COVERAGE)
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Kotlin.NETWORK))
    implementation(project(Projects.Implementation.Android.Core.DATABASE))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.CORE_JVM)
    implementation(Deps.Android.Arch.Database.Room.RUNTIME)
    implementation(Deps.Android.Arch.Database.Room.KTX)
    implementation(Deps.Android.Arch.Pagination.COMMON)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))

    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_5)
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT_5)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.Assert.STRIKT) { exclude(Deps.Test.Assert.Exclude.KOTLIN) }
    testImplementation(Deps.Test.Mock.MOCK_K)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Projects.Implementation.Kotlin.NETWORK, // Ignore change to 'api' advice.
                Projects.Implementation.Android.Core.DATABASE, // Ignore change to 'api' advice.
                Deps.Kotlin.Core.KOTLIN.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Arch.Pagination.COMMON.identifier() // Ignore change to 'api' advice.
            )
        }
    }
}
