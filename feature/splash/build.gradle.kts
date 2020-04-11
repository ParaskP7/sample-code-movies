@file:Suppress("InvalidPackageDeclaration", "ForbiddenComment")

/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Quality.DETEKT)
    // id(PluginIds.Test.Android.J_UNIT_5) // FIXME: Failed to notify project evaluation listener.
    id(PluginIds.Dependency.VERSIONS)
    id(PluginIds.Test.JACOCO)
    id(PluginIds.Test.COVERAGE)
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Project.Implementation.Android.Core.CORE))
    implementation(project(Project.Implementation.Android.Feature.MOVIES))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Di.Koin.Android.ANDROID)

    testImplementation(project(Project.TestImplementation.Kotlin.TEST))

    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT_5)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_5)
    testImplementation(Deps.Test.Assert.STRIKT)
    testImplementation(Deps.Test.Mock.MOCK_K)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
