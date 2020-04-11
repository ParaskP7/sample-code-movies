@file:Suppress("InvalidPackageDeclaration")

/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Kotlin.KAPT) // This plugin is required because of Glide.
    id(PluginIds.Quality.DETEKT)
    id(PluginIds.Dependency.VERSIONS)
    id(PluginIds.Test.JACOCO)
    id(PluginIds.Test.COVERAGE)
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
