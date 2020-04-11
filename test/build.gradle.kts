@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps

/* PLUGINS */

plugins {
    id(Plugins.Id.Kotlin.KOTLIN)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.DOMAIN))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.Test.TEST)
    implementation(Deps.Test.Spek.DSL)
    implementation(Deps.Test.Spek.J_UNIT_5)
    runtimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    implementation(Deps.Test.JUnit.J_UNIT_4)
    runtimeOnly(Deps.Test.JUnit.J_UNIT_5)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
