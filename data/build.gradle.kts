plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.github.ben-manes.versions")
    id("com.getkeepsafe.dexcount")
    id("io.gitlab.arturbosch.detekt")
}

apply("../config/gradle/android/android.gradle")
apply("../config/gradle/android/dexcount.gradle")
apply("../config/gradle/quality/lint.gradle")
apply("../config/gradle/quality/detekt.gradle")
apply("../config/gradle/dependencies/dependency_updates.gradle")

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
