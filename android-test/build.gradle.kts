@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.identifier

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

dependencies {
    implementation(Deps.Kotlin.Coroutines.Test.TEST)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.Test.CORE)
    implementation(Deps.Test.JUnit.J_UNIT_4)
    runtimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    implementation(Deps.Android.Test.Robolectric.ROBOLECTRIC) { exclude(Deps.Android.Test.Robolectric.Exclude.MAVEN) }
    implementation(Deps.Android.Test.Robolectric.ANNOTATIONS)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                Deps.Material.MATERIAL.identifier(), // Ignore remove advise. Required for theme purposes.
                Deps.Test.JUnit.J_UNIT_4.identifier() // Ignore remove advise. Required because of Robolectric.
            )
        }
        onIncorrectConfiguration {
            exclude(
                Deps.Kotlin.Coroutines.Test.TEST.identifier(), // Ignore change to 'api' advice.
                Deps.Android.Test.Robolectric.ROBOLECTRIC.identifier() // Ignore change to 'api' advice.
            )
        }
    }
}
