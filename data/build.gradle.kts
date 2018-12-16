plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.github.ben-manes.versions")
    id("com.getkeepsafe.dexcount")
    id("io.gitlab.arturbosch.detekt")
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
