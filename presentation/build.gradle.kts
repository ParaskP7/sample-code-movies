@file:Suppress("InvalidPackageDeclaration")

import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig

/* PLUGINS */

plugins {
    id(PluginIds.Android.APPLICATION)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Kotlin.Android.EXTENSIONS)
    id(PluginIds.Kotlin.KAPT) // This plugin is required because of Glide.
    id(PluginIds.Quality.DETEKT)
//    id(PluginIds.Test.Android.J_UNIT_5) // TODO: Update JUnit5 plugin and check again
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
    testImplementation()
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
    implementation(project(Project.Implementation.UTILS))
    implementation(project(Project.Implementation.ANDROID_UTILS))
    implementation(project(Project.Implementation.DOMAIN))
    implementation(project(Project.Implementation.DATA))
}

/* DEPENDENCIES - BUILD TYPE IMPLEMENTATION */

fun DependencyHandlerScope.debugImplementation() {
    debugImplementation(Deps.LeakCanary.LEAK_CANARY)
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationMaterial()
    implementationAndroidCore()
    implementationAndroidKtx()
    implementationAndroidArch()
    implementationDi()
    implementationGlide()
    implementationUtil()
    implementationLog()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.ANDROID)
}

fun DependencyHandlerScope.implementationMaterial() {
    implementation(Deps.Material.MATERIAL)
}

fun DependencyHandlerScope.implementationAndroidCore() {
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.RECYCLER_VIEW)
    implementation(Deps.Android.Core.CARD_VIEW)
    implementation(Deps.Android.Core.CONSTRAINT_LAYOUT)
}

fun DependencyHandlerScope.implementationAndroidKtx() {
    implementation(Deps.Android.Ktx.CORE)
}

fun DependencyHandlerScope.implementationAndroidArch() {
    implementation(Deps.Android.Arch.Core.Lifecycle.LIVE_DATA)
    implementation(Deps.Android.Arch.Core.Lifecycle.VIEW_MODEL)
    implementation(Deps.Android.Arch.Core.Lifecycle.VIEW_MODEL_KTX)
    implementation(Deps.Android.Arch.Core.Lifecycle.PROCESS)
}

fun DependencyHandlerScope.implementationDi() {
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Di.Koin.Android.VIEW_MODEL)
}

fun DependencyHandlerScope.implementationGlide() {
    implementation(Deps.Image.Glide.GLIDE)
    kapt(Deps.Image.Glide.GLIDE_COMPILER)
}

fun DependencyHandlerScope.implementationUtil() {
    implementation(Deps.Util.MONTH_YEAR_PICKER)
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
    testImplementationKotlin()
    testImplementationSpek()
    testImplementationJUnit()
    testImplementationTest()
    testImplementationMock()
    testImplementationAndroidArch()
    testImplementationRobolectric()
}

fun DependencyHandlerScope.testImplementationKotlin() {
    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
}

fun DependencyHandlerScope.testImplementationSpek() {
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
}

fun DependencyHandlerScope.testImplementationJUnit() {
    testImplementation(Deps.Test.JUnit.J_UNIT)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_VINTAGE)
}

fun DependencyHandlerScope.testImplementationTest() {
    testImplementation(Deps.Test.Assert.STRIKT)
}

fun DependencyHandlerScope.testImplementationMock() {
    testImplementation(Deps.Test.Mock.MOCK_K)
}

fun DependencyHandlerScope.testImplementationAndroidArch() {
    testImplementation(Deps.Android.Arch.Test.CORE_TESTING)
}

fun DependencyHandlerScope.testImplementationRobolectric() {
    testImplementation(Deps.Android.Test.CORE)
    testImplementation(Deps.Android.Test.ROBOLECTRIC)
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
