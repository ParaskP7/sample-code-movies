@file:Suppress("InvalidPackageDeclaration", "ForbiddenComment", "FunctionMaxLength")

import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig

/* PLUGINS */

plugins {
    id(PluginIds.Android.APPLICATION)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Quality.DETEKT)
    // id(PluginIds.Test.Android.J_UNIT_5)  // FIXME: Failed to notify project evaluation listener.
    id(PluginIds.Dependency.VERSIONS)
}

/* ANDROID */

android {
    defaultConfig { defaultConfig() }
    buildTypes { buildTypes() }
}

/* DEPENDENCIES */

dependencies {
    projectImplementation()
    debugImplementation()
    implementation()
    testProjectImplementation()
    androidTestImplementation()
    plugins()
}

/* *********************************************************************************************************************** */

/* CONFIGURATION EXTENSION FUNCTIONS - ANDROID */

fun DefaultConfig.defaultConfig() {
    applicationId = App.APPLICATION_ID
    versionCode = App.Version.CODE
    versionName = App.Version.NAME
    testInstrumentationRunner = Android.DefaultConfig.Test.INSTRUMENTATION_RUNNER
}

fun NamedDomainObjectContainer<BuildType>.buildTypes() {
    named(Android.BuildTypes.DEBUG) {
        applicationIdSuffix = App.Debug.Suffix.APPLICATION_ID
        versionNameSuffix = App.Debug.Suffix.VERSION_NAME
        isDebuggable = true
    }
    named(Android.BuildTypes.RELEASE) {
        isMinifyEnabled = false
        proguardFiles(getDefaultProguardFile(Files.Txt.PROGUARD_ANDROID, layout.buildDirectory), Files.Pro.PROGUARD_RULES)
    }
}

/* DEPENDENCIES - PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.projectImplementation() {
    kotlinProjectImplementation()
    androidProjectImplementation()
}

fun DependencyHandlerScope.kotlinProjectImplementation() {
    implementation(project(Project.Implementation.Kotlin.UTILS))
    implementation(project(Project.Implementation.Kotlin.DOMAIN))
}

fun DependencyHandlerScope.androidProjectImplementation() {
    androidProjectImplementationCore()
    androidProjectImplementationFeature()
}

fun DependencyHandlerScope.androidProjectImplementationCore() {
    implementation(project(Project.Implementation.Android.Core.ANDROID_UTILS))
    implementation(project(Project.Implementation.Android.Core.DATA))
    implementation(project(Project.Implementation.Android.Core.CORE))
}

fun DependencyHandlerScope.androidProjectImplementationFeature() {
    implementation(project(Project.Implementation.Android.Feature.SPLASH))
    implementation(project(Project.Implementation.Android.Feature.MOVIES))
    implementation(project(Project.Implementation.Android.Feature.MOVIE_DETAILS))
}

/* DEPENDENCIES - BUILD TYPE IMPLEMENTATION */

fun DependencyHandlerScope.debugImplementation() {
    debugImplementation(Deps.LeakCanary.LEAK_CANARY)
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationAndroidCore()
    implementationAndroidArch()
    implementationDi()
    implementationLog()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.Core.KOTLIN)
}

fun DependencyHandlerScope.implementationAndroidCore() {
    implementation(Deps.Android.Core.APP_COMPAT)
}

fun DependencyHandlerScope.implementationAndroidArch() {
    implementation(Deps.Android.Arch.Core.Lifecycle.PROCESS)
}

fun DependencyHandlerScope.implementationDi() {
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Di.Koin.Android.VIEW_MODEL)
}

fun DependencyHandlerScope.implementationLog() {
    implementation(Deps.Log.TIMBER)
}

/* DEPENDENCIES - TEST PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.testProjectImplementation() {
    kotlinTestProjectImplementation()
}

fun DependencyHandlerScope.kotlinTestProjectImplementation() {
    testImplementation(project(Project.TestImplementation.Kotlin.TEST))
}

/* DEPENDENCIES - ANDROID TEST IMPLEMENTATION */

fun DependencyHandlerScope.androidTestImplementation() {
    androidTestImplementationTest()
    androidTestImplementationMock()
}

fun DependencyHandlerScope.androidTestImplementationTest() {
    androidTestImplementation(Deps.Android.Test.CORE)
    androidTestImplementation(Deps.Android.Test.J_UNIT)
    androidTestImplementation(Deps.Android.Test.ESPRESSO)
}

fun DependencyHandlerScope.androidTestImplementationMock() {
    androidTestImplementation(Deps.Test.Assert.STRIKT)
}

/* DEPENDENCIES - PLUGINS */

fun DependencyHandlerScope.plugins() {
    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
