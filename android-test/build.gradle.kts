@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps

/* PLUGINS */

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

/* DEPENDENCIES */

dependencies {
    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.Test.TEST)
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Test.CORE)
    implementation(Deps.Test.Spek.DSL)
    implementation(Deps.Test.Spek.J_UNIT_5)
    runtimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    implementation(Deps.Android.Arch.Test.CORE_TESTING)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
