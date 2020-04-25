@file:Suppress("InvalidPackageDeclaration")

import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig
import com.android.build.gradle.internal.dsl.PackagingOptions
import io.petros.movies.config.android.Android
import io.petros.movies.config.android.App
import io.petros.movies.config.deps.Deps
import io.petros.movies.config.dirs.Files

/* PLUGINS */

plugins {
    id(Plugins.Id.Android.APPLICATION)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

/* ANDROID */

android {
    defaultConfig { defaultConfig() }
    buildTypes { buildTypes() }
    packagingOptions { packagingOptions() }
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.DOMAIN))
    implementation(project(Deps.Project.Implementation.Android.Core.DATA))
    implementation(project(Deps.Project.Implementation.Android.Core.CORE))
    implementation(project(Deps.Project.Implementation.Android.Feature.SPLASH))
    implementation(project(Deps.Project.Implementation.Android.Feature.MOVIES))
    implementation(project(Deps.Project.Implementation.Android.Feature.MOVIE_DETAILS))

    debugImplementation(Deps.LeakCanary.LEAK_CANARY)

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Android.Arch.Core.Lifecycle.PROCESS)
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Log.TIMBER)

    androidTestImplementation(Deps.Android.Test.CORE)
    androidTestImplementation(Deps.Android.Test.J_UNIT)
    androidTestImplementation(Deps.Android.Test.Espresso.CORE)
    androidTestImplementation(Deps.Test.Assert.STRIKT)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}

/* *********************************************************************************************************************** */

/* CONFIGURATION EXTENSION FUNCTIONS */

fun DefaultConfig.defaultConfig() {
    applicationId = App.APPLICATION_ID
    versionCode = App.Version.CODE
    versionName = App.Version.NAME
    testInstrumentationRunner = Android.DefaultConfig.Test.CUSTOM_INSTRUMENTATION_RUNNER
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

fun PackagingOptions.packagingOptions() {
    exclude(Android.PackagingOption.Exclude.LICENCE)
    exclude(Android.PackagingOption.Exclude.LICENCE_NOTICE)
}
