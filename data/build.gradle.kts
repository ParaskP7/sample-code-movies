plugins {
    id(PluginIds.ANDROID_LIBRARY)
    id(PluginIds.KOTLIN_ANDROID)
    id(PluginIds.KOTLIN_ANDROID_EXTENSIONS)
    id(PluginIds.KOTLIN_KAPT)
    id(PluginIds.VERSIONS)
    id(PluginIds.DEXCOUNT)
    id(PluginIds.DETEKT)
}

apply(Config.GRADLE_ANDROID)
apply(Config.GRADLE_DEXCOUNT)
apply(Config.GRADLE_LINT)
apply(Config.GRADLE_DETEKT)
apply(Config.GRADLE_DEPENDENCY_UPDATES)

kapt {
    useBuildCache = true
}

dependencies {
    implementation(project(Project.DOMAIN))

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

    testImplementation(project(Project.TEST))

    testImplementation(Deps.Test.J_UNIT)
    testImplementation(Deps.Test.ASSERT_J)
    testImplementation(Deps.Mock.MOCKITO_KOTLIN, {
        exclude(ExcludedDeps.Group.Jetbrains.KOTLIN, ExcludedDeps.Module.Kotlin.REFLECT)
    })

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
