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
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Net.OkHttp.OK_HTTP)
    implementation(Deps.Net.OkHttp.LOGGING_INTERCEPTOR)
    implementation(Deps.Net.Gson.GSON)
    implementation(Deps.Net.Rest.RETROFIT)
    implementation(Deps.Net.Rest.RETROFIT_GSON)

    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))

    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_5)
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT_5)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.Integration.MOCK_WEB_SERVER)
    testImplementation(Deps.Test.Assert.STRIKT) { exclude(Deps.Test.Assert.Exclude.KOTLIN) }
    testImplementation(Deps.Test.Mock.MOCK_K)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Deps.Kotlin.Core.KOTLIN.identifier(), // Ignore change to 'api' advice.
                Deps.Di.Koin.Kotlin.CORE.identifier() // Ignore change to 'api' advice.
            )
        }
    }
}
