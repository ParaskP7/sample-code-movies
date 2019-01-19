import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.DefaultConfig
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile

import org.jetbrains.kotlin.gradle.plugin.KaptExtension

/* PLUGINS */

plugins {
    id(PluginIds.Android.APPLICATION)
    id(PluginIds.Android.DEXCOUNT)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Kotlin.Android.EXTENSIONS)
    id(PluginIds.Kotlin.KAPT)
    id(PluginIds.Quality.DETEKT)
    id(PluginIds.Dependency.VERSIONS)
}

/* ANDROID */

android {
    defaultConfig { defaultConfig() }
    buildTypes { buildTypes() }
}

/* CONFIGURATIONS */

configurations.all {
    leakCanary()
}

/* DEPENDENCIES */

dependencies {
    projectImplementation()
    buildTypeImplementation()
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
        proguardFiles(getDefaultProguardFile(Files.Txt.PROGUARD_ANDROID, project), Files.Pro.PROGUARD_RULES)
    }
}

/* CONFIGURATION EXTENSION FUNCTIONS - LEAK CANARY */

fun org.gradle.api.artifacts.Configuration.leakCanary() {
    if (name.contains(Configuration.Test.UNIT_TEST) || name.contains(Configuration.Test.ANDROID_TEST)) {
        resolutionStrategy {
            eachDependency {
                if (requested.group == Deps.LeakCanary.GROUP && requested.name == Deps.LeakCanary.NAME) {
                    useTarget(Deps.LeakCanary.RELEASE)
                }
            }
        }
    }
}

/* DEPENDENCIES - PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.projectImplementation() {
    implementation(project(Project.Implementation.DOMAIN))
    implementation(project(Project.Implementation.DATA))
}

/* DEPENDENCIES - BUILD TYPE IMPLEMENTATION */

fun DependencyHandlerScope.buildTypeImplementation() {
    buildTypeImplementationLeakCanary()
}

fun DependencyHandlerScope.buildTypeImplementationLeakCanary() {
    debugImplementation(Deps.LeakCanary.DEBUG)
    releaseImplementation(Deps.LeakCanary.RELEASE)
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationMaterial()
    implementationAndroid()
    implementationAndroidKtx()
    implementationAndroidArch()
    implementationDi()
    implementationNet()
    implementationRetrofit()
    implementationGlide()
    implementationUtil()
    implementationLog()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Kotlin.Coroutines.ANDROID)
}

fun DependencyHandlerScope.implementationMaterial() {
    implementation(Deps.Material.MATERIAL)
}

fun DependencyHandlerScope.implementationAndroid() {
    implementation(Deps.Android.APP_COMPAT)
    implementation(Deps.Android.RECYCLER_VIEW)
    implementation(Deps.Android.CARD_VIEW)
    implementation(Deps.Android.CONSTRAINT_LAYOUT)
}

fun DependencyHandlerScope.implementationAndroidKtx() {
    implementation(Deps.Android.Ktx.CORE)
}

fun DependencyHandlerScope.implementationAndroidArch() {
    implementation(Deps.Android.Arch.LIFECYCLE_EXTENSIONS)
}

fun DependencyHandlerScope.implementationDi() {
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Di.Koin.Android.VIEW_MODEL)
}

fun DependencyHandlerScope.implementationNet() {
    implementation(Deps.Net.GSON)
    implementation(Deps.Net.OK_HTTP_LOGGING)
}

fun DependencyHandlerScope.implementationRetrofit() {
    implementation(Deps.Net.Rest.RETROFIT)
}

fun DependencyHandlerScope.implementationGlide() {
    implementation(Deps.Image.GLIDE)
    kapt(Deps.Image.GLIDE_COMPILER)
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
    testImplementationTest()
    testImplementationMock()
    testImplementationAndroidArch()
    testImplementationRobolectric()
}

fun DependencyHandlerScope.testImplementationTest() {
    testImplementation(Deps.Test.J_UNIT)
    testImplementation(Deps.Test.STRIKT)
}

fun DependencyHandlerScope.testImplementationMock() {
    testImplementation(Deps.Mock.MOCK_K)
}

fun DependencyHandlerScope.testImplementationAndroidArch() {
    testImplementation(Deps.Android.Arch.Test.CORE_TESTING)
}

fun DependencyHandlerScope.testImplementationRobolectric() {
    testImplementation(Deps.Android.Test.CORE)
    testImplementation(Deps.Robolectric.ROBOLECTRIC)
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
    androidTestImplementation(Deps.Test.STRIKT)
}

/* DEPENDENCIES - PLUGINS */

fun DependencyHandlerScope.plugins() {
    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
