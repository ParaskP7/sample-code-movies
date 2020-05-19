@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

dependencies {
    implementation(project(Deps.Project.TestImplementation.Kotlin.TEST))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.Test.TEST)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.Core.RECYCLER_VIEW)
    implementation(Deps.Android.Test.CORE)
    implementation(Deps.Log.TIMBER)
    implementation(Deps.Test.Spek.DSL)
    implementation(Deps.Test.Spek.J_UNIT_5)
    runtimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    implementation(Deps.Android.Arch.Test.CORE_TESTING)
    implementation(Deps.Android.Test.Espresso.CORE)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
