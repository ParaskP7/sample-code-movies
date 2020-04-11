@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps

/* PLUGINS */

plugins {
    id(Plugins.Id.Kotlin.KOTLIN)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Test.JACOCO)
    id(Plugins.Id.Test.COVERAGE)
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.UTILS))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Di.Koin.Kotlin.CORE)

    testImplementation(project(Deps.Project.TestImplementation.Kotlin.TEST))

    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT_5)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_5)
    testImplementation(Deps.Test.Assert.STRIKT)
    testImplementation(Deps.Test.Mock.MOCK_K)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
