@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Projects
import io.petros.movies.config.deps.identifier

plugins {
    id(Plugins.Id.Kotlin.KOTLIN)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Test.JACOCO)
    id(Plugins.Id.Test.COVERAGE)
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.UTILS))

    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.CORE_JVM)
    implementation(Deps.Android.Arch.Pagination.COMMON)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Di.Koin.Kotlin.CORE_JVM)

    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))

    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
    testImplementation(Deps.Kotlin.Coroutines.Test.TEST_JVM)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.Assert.STRIKT) { exclude(Deps.Test.Assert.Exclude.KOTLIN) }
    testImplementation(Deps.Test.Mock.MOCK_K)
    testImplementation(Deps.Test.Mock.DSL_JVM)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Deps.Di.Koin.Kotlin.CORE.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE_JVM.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
