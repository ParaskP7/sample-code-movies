@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.identifier

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                Deps.Material.MATERIAL.identifier() // Ignore remove advise. Required for theme purposes.
            )
        }
        onUsedTransitiveDependencies {
            exclude(
                Deps.Test.Hamcrest.CORE.identifier(), // Ignore add to 'api' advice. Otherwise, Espresso fails.
                Deps.Test.Hamcrest.LIBRARY.identifier() // Ignore add to 'implementation' advice. Otherwise, Espresso fails.
            )
        }
        onIncorrectConfiguration {
            exclude(
                Deps.Kotlin.Coroutines.Test.TEST.identifier(), // Ignore change to 'api' advice.
                Deps.Test.Spek.DSL.identifier() // Ignore change to 'api' advice.
            )
        }
    }
}

dependencies {
    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.Test.TEST)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.Core.RECYCLER_VIEW)
    implementation(Deps.Android.Arch.Core.RUNTIME)
    implementation(Deps.Android.Test.CORE)
    implementation(Deps.Android.Test.Espresso.CORE)
    implementation(Deps.Log.TIMBER)
    implementation(Deps.Test.Spek.DSL)
    implementation(Deps.Test.Spek.J_UNIT_5)
    runtimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}
