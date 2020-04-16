@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps

/* PLUGINS */

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Kotlin.KAPT) // This plugin is required because of Glide.
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Test.JACOCO)
    id(Plugins.Id.Test.COVERAGE)
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.DOMAIN))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.RECYCLER_VIEW)
    implementation(Deps.Image.Glide.GLIDE)
    kapt(Deps.Image.Glide.GLIDE_COMPILER)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Deps.Project.TestImplementation.Kotlin.TEST))
    testImplementation(project(Deps.Project.TestImplementation.Android.ANDROID_TEST))

    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testImplementation(Deps.Test.Assert.STRIKT)
    testImplementation(Deps.Test.Mock.MOCK_K)
    testImplementation(Deps.Android.Test.ROBOLECTRIC)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}