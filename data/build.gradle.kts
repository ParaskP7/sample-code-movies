import com.android.build.gradle.internal.dsl.BuildType

import java.io.FileInputStream
import java.util.Properties

/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Android.DEXCOUNT)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Kotlin.Android.EXTENSIONS)
    id(PluginIds.Kotlin.KAPT)
    // TODO: Re-add Detekt plugin.
    id(PluginIds.Test.Android.J_UNIT_5)
}

/* ANDROID */

android {
    buildTypes { buildTypes() }
}

/* DEPENDENCIES */

dependencies {
    projectImplementation()
    implementation()
    testProjectImplementation()
    testImplementation()
}

/* *********************************************************************************************************************** */

/* CONFIGURATION EXTENSION FUNCTIONS - ANDROID */

fun NamedDomainObjectContainer<BuildType>.buildTypes() {
    val themoviedbApiProperties = Properties()
    themoviedbApiProperties.load(FileInputStream(file(Config.Keys.TheMoviesDb.API_FILE_PATH)))
    val themoviedbApiKey = themoviedbApiProperties[Keys.TheMoviesDb.Property.API_KEY].asString()
    named(Android.BuildTypes.DEBUG) {
        buildConfigField(BuildConfig.Field.STRING, Config.Keys.TheMoviesDb.API_KEY_CONST, themoviedbApiKey)
    }
    named(Android.BuildTypes.RELEASE) {
        buildConfigField(BuildConfig.Field.STRING, Config.Keys.TheMoviesDb.API_KEY_CONST, themoviedbApiKey)
    }
}

/* DEPENDENCIES - PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.projectImplementation() {
    implementation(project(Project.Implementation.DOMAIN))
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationAndroid()
    implementationDi()
    implementationNet()
    implementationRetrofit()
    implementationLog()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
}

fun DependencyHandlerScope.implementationAndroid() {
    implementation(Deps.Android.APP_COMPAT)
}

fun DependencyHandlerScope.implementationDi() {
    implementation(Deps.Di.Koin.Android.ANDROID)
}

fun DependencyHandlerScope.implementationNet() {
    implementation(Deps.Net.OK_HTTP_LOGGING)
}

fun DependencyHandlerScope.implementationRetrofit() {
    implementation(Deps.Net.Rest.RETROFIT)
    implementation(Deps.Net.Rest.RETROFIT_GSON)
    implementation(Deps.Net.Rest.RETROFIT_COROUTINES)
}

fun DependencyHandlerScope.implementationLog() {
    implementation(Deps.Log.TIMBER)
}
/* DEPENDENCIES - TEST PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.testProjectImplementation() {
    testImplementation(project(Project.TestImplementation.TEST))
}

/* DEPENDENCIES - TEST IMPLEMENTATION */

fun DependencyHandlerScope.testImplementation() {
    testImplementationSpek()
    testImplementationTest()
    testImplementationMock()
}

fun DependencyHandlerScope.testImplementationSpek() {
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT)
    testRuntimeOnly(Deps.Kotlin.KOTLIN_REFLECT)
}

fun DependencyHandlerScope.testImplementationTest() {
    testImplementation(Deps.Test.STRIKT)
}

fun DependencyHandlerScope.testImplementationMock() {
    testImplementation(Deps.Mock.MOCK_K)
}
