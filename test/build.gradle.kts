@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.identifier

plugins {
    id(Plugins.Id.Kotlin.KOTLIN)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Deps.Project.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Deps.Kotlin.Coroutines.Test.TEST.identifier(), // Ignore change to 'api' advice.
                Deps.Test.JUnit.J_UNIT_4.identifier(), // Ignore change to 'api' advice.
                Deps.Test.Spek.DSL.identifier(), // Ignore change to 'api' advice.
                Deps.Test.Integration.MOCK_WEB_SERVER.identifier() // Ignore change to 'api' advice.
            )
        }
    }
}

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.DOMAIN))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.Test.TEST)
    implementation(Deps.Net.OkHttp.OK_HTTP)
    implementation(Deps.Net.Gson.GSON)
    implementation(Deps.Net.Rest.RETROFIT)
    implementation(Deps.Net.Rest.RETROFIT_GSON)
    implementation(Deps.Test.JUnit.J_UNIT_4)
    runtimeOnly(Deps.Test.JUnit.J_UNIT_5)
    implementation(Deps.Test.Spek.DSL)
    implementation(Deps.Test.Spek.J_UNIT_5)
    runtimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    implementation(Deps.Test.Integration.MOCK_WEB_SERVER)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}
