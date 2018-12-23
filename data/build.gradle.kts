import com.android.build.gradle.internal.dsl.BuildType

import java.io.FileInputStream
import java.util.Properties

/* EXTENSIONS FUNCTIONS */

fun Any?.asString() = "\"$this\""

/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Android.DEXCOUNT)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Kotlin.Android.EXTENSIONS)
    id(PluginIds.Kotlin.KAPT)
    id(PluginIds.Quality.DETEKT)
    id(PluginIds.Dependency.VERSIONS)
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
    plugins()
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
    implementationRx()
    implementationNet()
    implementationRetrofit()
    implementationLog()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.KOTLIN)
}

fun DependencyHandlerScope.implementationAndroid() {
    implementation(Deps.Android.APP_COMPAT)
}

fun DependencyHandlerScope.implementationDi() {
    implementation(Deps.Di.DAGGER)
    kapt(Deps.Di.DAGGER_COMPILER)
    implementation(Deps.Di.DAGGER_ANDROID)
    kapt(Deps.Di.DAGGER_ANDROID_PROCESSOR)
}

fun DependencyHandlerScope.implementationRx() {
    implementation(Deps.Rx.RX_JAVA)
    implementation(Deps.Rx.RX_ANDROID)
}

fun DependencyHandlerScope.implementationNet() {
    implementation(Deps.Net.GSON)
    implementation(Deps.Net.OK_HTTP_LOGGING)
}

fun DependencyHandlerScope.implementationRetrofit() {
    implementation(Deps.Net.Rest.RETROFIT)
    implementation(Deps.Net.Rest.RETROFIT_GSON)
    implementation(Deps.Net.Rest.RETROFIT_RX)
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
    testImplementationTest()
    testImplementationMock()
}

fun DependencyHandlerScope.testImplementationTest() {
    testImplementation(Deps.Test.J_UNIT)
    testImplementation(Deps.Test.ASSERT_J)
}

fun DependencyHandlerScope.testImplementationMock() {
    testImplementation(Deps.Mock.MOCKITO_KOTLIN)
}

/* DEPENDENCIES - PLUGINS */

fun DependencyHandlerScope.plugins() {
    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}

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
