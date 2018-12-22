/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Android.DEXCOUNT)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Kotlin.Android.EXTENSIONS)
    id(PluginIds.Kotlin.KAPT)
    id(PluginIds.Dependency.VERSIONS)
    id(PluginIds.Quality.DETEKT)
}

/* KAPT */

kapt {
    useBuildCache = true
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Project.Implementation.DOMAIN))

    implementation(Deps.Kotlin.KOTLIN)
    implementation(Deps.Android.APP_COMPAT)
    implementation(Deps.Di.DAGGER)
    kapt(Deps.Di.DAGGER_COMPILER)
    implementation(Deps.Di.DAGGER_ANDROID)
    kapt(Deps.Di.DAGGER_ANDROID_PROCESSOR)
    implementation(Deps.Rx.RX_JAVA)
    implementation(Deps.Rx.RX_ANDROID)
    implementation(Deps.Net.GSON)
    implementation(Deps.Net.OK_HTTP_LOGGING)
    implementation(Deps.Net.Rest.RETROFIT)
    implementation(Deps.Net.Rest.RETROFIT_GSON)
    implementation(Deps.Net.Rest.RETROFIT_RX)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Project.TestImplementation.TEST))

    testImplementation(Deps.Test.J_UNIT)
    testImplementation(Deps.Test.ASSERT_J)
    testImplementation(Deps.Mock.MOCKITO_KOTLIN, {
        exclude(ExcludedDeps.Group.Jetbrains.KOTLIN, ExcludedDeps.Module.Kotlin.REFLECT)
    })

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
