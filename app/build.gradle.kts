@file:Suppress("InvalidPackageDeclaration")

import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig
import io.petros.movies.config.android.Android
import io.petros.movies.config.android.App
import io.petros.movies.config.android.BuildConfig
import io.petros.movies.config.android.Keys
import io.petros.movies.config.deps.Deps
import io.petros.movies.config.dirs.Files
import io.petros.movies.config.utils.asString
import java.io.FileInputStream
import java.util.*

plugins {
    id(Plugins.Id.Android.APPLICATION)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

android {
    defaultConfig { defaultConfig() }
    buildTypes { buildTypes() }
}

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.DOMAIN))
    implementation(project(Deps.Project.Implementation.Android.Core.DATA))
    implementation(project(Deps.Project.Implementation.Android.Core.CORE))
    implementation(project(Deps.Project.Implementation.Android.Feature.SPLASH))
    implementation(project(Deps.Project.Implementation.Android.Feature.MOVIES))
    implementation(project(Deps.Project.Implementation.Android.Feature.MOVIE_DETAILS))

    debugImplementation(Deps.LeakCanary.LEAK_CANARY)

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.CONSTRAINT_LAYOUT)
    implementation(Deps.Android.Arch.Core.Lifecycle.PROCESS)
    implementation(Deps.Android.Arch.Core.Navigation.FRAGMENT_KTX)
    implementation(Deps.Android.Arch.Core.Navigation.UI_KTX)
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Log.TIMBER)

    androidTestImplementation(project(Deps.Project.TestImplementation.Kotlin.TEST))
    androidTestImplementation(project(Deps.Project.TestImplementation.Android.ANDROID_TEST))

    androidTestImplementation(Deps.Android.Test.CORE)
    androidTestImplementation(Deps.Android.Test.RULES)
    androidTestImplementation(Deps.Android.Test.J_UNIT)
    androidTestImplementation(Deps.Android.Test.Espresso.CORE)
    androidTestImplementation(Deps.Android.Test.Espresso.CONTRIB)
    androidTestImplementation(Deps.Test.Integration.MOCK_WEB_SERVER)
    androidTestImplementation(Deps.Test.Assert.STRIKT)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}

/* CONFIGURATION EXTENSION FUNCTIONS */

fun DefaultConfig.defaultConfig() {
    applicationId = App.APPLICATION_ID
    versionCode = App.Version.CODE
    versionName = App.Version.NAME
    testInstrumentationRunner = Android.DefaultConfig.Test.CUSTOM_INSTRUMENTATION_RUNNER
}

fun NamedDomainObjectContainer<BuildType>.buildTypes() {
    val themoviedbApiProperties = Properties()
    themoviedbApiProperties.load(FileInputStream(file(Keys.TheMoviesDb.Config.API_FILE_PATH)))
    val themoviedbApiKey = themoviedbApiProperties[Keys.TheMoviesDb.Property.API_KEY].asString()
    named(Android.BuildTypes.DEBUG) {
        applicationIdSuffix = App.Debug.Suffix.APPLICATION_ID
        versionNameSuffix = App.Debug.Suffix.VERSION_NAME
        isDebuggable = true
        buildConfigField(BuildConfig.Field.STRING, Keys.TheMoviesDb.Config.API_KEY_CONST, themoviedbApiKey)
    }
    named(Android.BuildTypes.RELEASE) {
        isMinifyEnabled = false
        proguardFiles(getDefaultProguardFile(Files.Txt.PROGUARD_ANDROID, layout.buildDirectory), Files.Pro.PROGUARD_RULES)
        buildConfigField(BuildConfig.Field.STRING, Keys.TheMoviesDb.Config.API_KEY_CONST, themoviedbApiKey)
    }
}
