@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Projects

plugins {
    id(Plugins.Id.Kotlin.KOTLIN)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Test.JACOCO)
    id(Plugins.Id.Test.COVERAGE)
}

dependencies {
    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))

    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.Assert.STRIKT) { exclude(Deps.Test.Assert.Exclude.KOTLIN) }
    testImplementation(Deps.Test.Mock.MOCK_K)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}
