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

/* KAPT */

kapt {
    dart()
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Project.Implementation.DOMAIN))
    implementation(project(Project.Implementation.DATA))

    debugImplementation(Deps.LeakCanary.DEBUG)
    releaseImplementation(Deps.LeakCanary.RELEASE)

    implementation(Deps.Kotlin.KOTLIN)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.APP_COMPAT)
    implementation(Deps.Android.RECYCLER_VIEW)
    implementation(Deps.Android.CARD_VIEW)
    implementation(Deps.Android.CONSTRAINT_LAYOUT)
    implementation(Deps.Android.Ktx.CORE)
    implementation(Deps.Android.Arch.LIFECYCLE_EXTENSIONS)
    implementation(Deps.Di.DAGGER)
    kapt(Deps.Di.DAGGER_COMPILER)
    implementation(Deps.Di.DAGGER_ANDROID)
    kapt(Deps.Di.DAGGER_ANDROID_PROCESSOR)
    implementation(Deps.Rx.RX_JAVA)
    implementation(Deps.Rx.RX_ANDROID)
    implementation(Deps.Net.GSON)
    implementation(Deps.Net.OK_HTTP_LOGGING)
    implementation(Deps.Net.Rest.RETROFIT)
    implementation(Deps.Image.GLIDE)
    kapt(Deps.Image.GLIDE_COMPILER)
    implementation(Deps.Extras.DART)
    kapt(Deps.Extras.DART_PROCESSOR)
    implementation(Deps.Extras.HENSON)
    kapt(Deps.Extras.HENSON_PROCESSOR)
    implementation(Deps.Util.MONTH_YEAR_PICKER)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Project.TestImplementation.TEST))

    testImplementation(Deps.Test.J_UNIT)
    testImplementation(Deps.Test.ASSERT_J)
    testImplementation(Deps.Mock.MOCKITO_KOTLIN)
    testImplementation(Deps.Android.Arch.Test.CORE_TESTING)
    testImplementation(Deps.Android.Test.CORE)
    testImplementation(Deps.Robolectric.ROBOLECTRIC)

    androidTestImplementation(Deps.Android.Test.CORE)
    androidTestImplementation(Deps.Android.Test.J_UNIT)
    androidTestImplementation(Deps.Android.Test.ESPRESSO)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}

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

/* CONFIGURATION EXTENSION FUNCTIONS - DART */

fun KaptExtension.dart() {
    arguments { arg(Config.Dart.Kapt.NAME, App.APPLICATION_ID) }
}
