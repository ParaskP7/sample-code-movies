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
    implementation(project(":domain"))

    implementation(Deps.kotlin)
    implementation(Deps.androidAppCompat)
    implementation(Deps.diDagger)
    kapt(Deps.diDaggerCompiler)
    implementation(Deps.diDaggerAndroid)
    kapt(Deps.diDaggerAndroidProcessor)
    implementation(Deps.rxJava)
    implementation(Deps.rxAndroid)
    implementation(Deps.netGson)
    implementation(Deps.netOkHttpLogging)
    implementation(Deps.restRetrofit)
    implementation(Deps.restRetrofitGson)
    implementation(Deps.restRetrofitRx)
    implementation(Deps.logTimber)

    testImplementation(project(":test"))

    testImplementation(Deps.testJUnit)
    testImplementation(Deps.testAssertJ)
    testImplementation(Deps.mockMockitoKotlin, {
        exclude(ExcludedDeps.groupJetbrainsKotlin, ExcludedDeps.moduleKotlinReflect)
    })

    detektPlugins(Deps.pluginDetektFormatting)
}
