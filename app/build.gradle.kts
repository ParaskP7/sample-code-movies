@file:Suppress("InvalidPackageDeclaration")

import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig

/* PLUGINS */

plugins {
    id(PluginIds.Android.APPLICATION)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Quality.DETEKT)
    id(PluginIds.Dependency.VERSIONS)
}

/* ANDROID */

android {
    defaultConfig { defaultConfig() }
    buildTypes { buildTypes() }
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Project.Implementation.Kotlin.DOMAIN))
    implementation(project(Project.Implementation.Android.Core.DATA))
    implementation(project(Project.Implementation.Android.Core.CORE))
    implementation(project(Project.Implementation.Android.Feature.SPLASH))
    implementation(project(Project.Implementation.Android.Feature.MOVIES))
    implementation(project(Project.Implementation.Android.Feature.MOVIE_DETAILS))

    debugImplementation(Deps.LeakCanary.LEAK_CANARY)

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Android.Arch.Core.Lifecycle.PROCESS)
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Log.TIMBER)

    androidTestImplementation(Deps.Android.Test.CORE)
    androidTestImplementation(Deps.Android.Test.J_UNIT)
    androidTestImplementation(Deps.Android.Test.ESPRESSO)
    androidTestImplementation(Deps.Test.Assert.STRIKT)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}

/* *********************************************************************************************************************** */

/* CONFIGURATION EXTENSION FUNCTIONS */

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
