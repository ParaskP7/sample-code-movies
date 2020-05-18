@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Test.Android.J_UNIT_5)
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Test.JACOCO)
    id(Plugins.Id.Test.COVERAGE)
}

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.UTILS))
    implementation(project(Deps.Project.Implementation.Kotlin.DOMAIN))
    implementation(project(Deps.Project.Implementation.Android.Core.ANDROID_UTILS))
    implementation(project(Deps.Project.Implementation.Android.Core.CORE))
    implementation(project(Deps.Project.Implementation.Android.Feature.PICKER))
    implementation(project(Deps.Project.Implementation.Android.Feature.MOVIE_DETAILS))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.CONSTRAINT_LAYOUT)
    implementation(Deps.Android.Ktx.CORE)
    implementation(Deps.Android.Arch.Core.Lifecycle.LIVE_DATA)
    implementation(Deps.Android.Arch.Core.Lifecycle.VIEW_MODEL)
    implementation(Deps.Android.Arch.Core.Lifecycle.VIEW_MODEL_KTX)
    implementation(Deps.Android.Arch.Core.Navigation.FRAGMENT_KTX)
    implementation(Deps.Android.Arch.Core.Navigation.UI_KTX)
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Di.Koin.Android.VIEW_MODEL)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Deps.Project.TestImplementation.Kotlin.TEST))
    testImplementation(project(Deps.Project.TestImplementation.Android.ANDROID_TEST))

    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_5)
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT_5)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.Assert.STRIKT)
    testImplementation(Deps.Test.Mock.MOCK_K)
    testImplementation(Deps.Android.Arch.Test.CORE_TESTING)
    testImplementation(Deps.Android.Test.Robolectric.ROBOLECTRIC)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
