import com.android.build.gradle.api.AndroidSourceSet
import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin as AndroidApplicationPlugin
import com.android.build.gradle.internal.CompileOptions
import com.android.build.gradle.internal.dsl.DefaultConfig
import com.android.build.gradle.internal.dsl.LintOptions
import com.android.build.gradle.internal.dsl.TestOptions
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin as AndroidLibraryPlugin

import com.getkeepsafe.dexcount.DexMethodCountExtension
import com.getkeepsafe.dexcount.DexMethodCountPlugin as DexcountPlugin

import com.github.benmanes.gradle.versions.VersionsPlugin

import io.gitlab.arturbosch.detekt.detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

import org.gradle.api.Project

import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsSubpluginIndicator as KotlinAndroidExtensionsPlugin
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin as KotlinKaptPlugin
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper as KotlinAndroidPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper as KotlinPlugin

/* BUILD SCRIPT */

buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri(Reps.Url.GRADLE) }
    }
    dependencies {
        classpath(Deps.Plugin.ANDROID)
        classpath(Deps.Plugin.KOTLIN)
        classpath(Deps.Plugin.VERSIONS)
        classpath(Deps.Plugin.DEXCOUNT)
        classpath(Deps.Plugin.DETEKT)
    }
}

/* PROJECTS CONFIGURATION */

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri(Reps.Url.GRADLE) }
    }
}

/* PROJECT PLUGINS CONFIGURATION */

subprojects {
    plugins.withType(KotlinPlugin::class) {
        log(PluginIds.Kotlin.KOTLIN)
        java { java() }
        tasks.getByName<Test>(Tasks.TEST) { testLogging() }
    }
    plugins.withType(KotlinKaptPlugin::class) {
        log(PluginIds.Kotlin.KAPT)
        kapt { kapt() }
    }
    plugins.withType(KotlinAndroidPlugin::class) {
        log(PluginIds.Kotlin.Android.ANDROID)
    }
    plugins.withType(KotlinAndroidExtensionsPlugin::class) {
        log(PluginIds.Kotlin.Android.EXTENSIONS)
    }
    plugins.withType(AndroidLibraryPlugin::class) {
        log(PluginIds.Android.LIBRARY)
        androidLibrary { androidLibrary() }
    }
    plugins.withType(AndroidApplicationPlugin::class) {
        log(PluginIds.Android.APPLICATION)
        androidApplication { androidApplication() }
    }
    plugins.withType(DexcountPlugin::class) {
        log(PluginIds.Android.DEXCOUNT)
        dexcount { dexcount() }
    }
    plugins.withType(DetektPlugin::class) {
        log(PluginIds.Quality.DETEKT)
        detekt { detekt() }
    }
    plugins.withType(VersionsPlugin::class) {
        log(PluginIds.Dependency.VERSIONS)
        apply(Config.Gradle.DEPENDENCY_UPDATES)
    }
}

/* *********************************************************************************************************************** */

/* PLUGINS EXTENSION FUNCTIONS */

fun Project.log(pluginId: String) {
    task("log-$pluginId") { println("<<< CONFIGURE WITH $pluginId PLUGIN >>>") }
}

fun Project.java(configure: JavaPluginExtension.() -> Unit) =
    extensions.configure(JavaPluginExtension::class.java, configure)

fun Project.kapt(configure: KaptExtension.() -> Unit) =
    extensions.configure(KaptExtension::class.java, configure)

fun Project.androidLibrary(configure: LibraryExtension.() -> Unit) =
    extensions.configure(LibraryExtension::class.java, configure)

fun Project.androidApplication(configure: AppExtension.() -> Unit) =
    extensions.configure(AppExtension::class.java, configure)

fun Project.dexcount(configure: DexMethodCountExtension.() -> Unit) =
    extensions.configure(DexMethodCountExtension::class.java, configure)

/* CONFIGURATION EXTENSION FUNCTIONS */

fun JavaPluginExtension.java() {
    sourceCompatibility = Java.version
    targetCompatibility = Java.version
}

fun Test.testLogging() {
    useJUnitPlatform { includeEngines(Tests.JUnit.Engine.SPEK) }
    testLogging {
        events(*Logs.events)
        setExceptionFormat(Logs.EXCEPTION_FORMAT)
    }
}

fun KaptExtension.kapt() {
    useBuildCache = true
}

fun LibraryExtension.androidLibrary() {
    compileSdkVersion(Android.Sdk.COMPLIE)
    testOptions.unitTests.isIncludeAndroidResources = true
    defaultConfig { defaultConfig() }
    compileOptions { compileOptions() }
    sourceSets { sourceSets() }
    testOptions { testOptions() }
    lintOptions { lintOptions() }
}

fun AppExtension.androidApplication() {
    compileSdkVersion(Android.Sdk.COMPLIE)
    testOptions.unitTests.isIncludeAndroidResources = true
    defaultConfig { defaultConfig() }
    compileOptions { compileOptions() }
    sourceSets { sourceSets() }
    testOptions { testOptions() }
    lintOptions { lintOptions() }
}

fun DexMethodCountExtension.dexcount() {
    format = Config.Dexcount.FORMAT
    includeClasses = true
    includeClassCount = true
    includeFieldCount = true
    includeTotalMethodCount = true
    orderByMethodCount = false
    verbose = false
    maxTreeDepth = Integer.MAX_VALUE
    teamCityIntegration = false
    teamCitySlug = null
    runOnEachPackage = true
    maxMethodCount = Config.Dexcount.MAX_METHOD_COUNT
}

fun DetektExtension.detekt() {
    toolVersion = Versions.Plugin.DETEKT
    config = files(Config.Detekt.CONFIG_FILE_PATH)
    filters = Config.Detekt.FILTERS
    disableDefaultRuleSets = false
    parallel = true
}

/* CONFIGURATION EXTENSION FUNCTIONS - ANDROID */

fun DefaultConfig.defaultConfig() {
    minSdkVersion(Android.Sdk.MIN)
    targetSdkVersion(Android.Sdk.TARGET)
}

fun CompileOptions.compileOptions() {
    sourceCompatibility = Java.version
    targetCompatibility = Java.version
}

fun NamedDomainObjectContainer<AndroidSourceSet>.sourceSets() {
    named(Sources.MAIN) {
        java.setSrcDirs(arrayListOf(Sources.Main.KOTLIN))
        resources.setSrcDirs(arrayListOf(Sources.Main.RESOURCES))
    }
    named(Sources.TEST) {
        java.setSrcDirs(arrayListOf(Sources.Test.KOTLIN, Sources.Robolectric.KOTLIN))
        resources.setSrcDirs(arrayListOf(Sources.Test.RESOURCES, Sources.Robolectric.RESOURCES))
    }
    named(Sources.ANDROID_TEST) {
        java.setSrcDirs(arrayListOf(Sources.Android.Test.KOTLIN))
        resources.setSrcDirs(arrayListOf(Sources.Android.Test.RESOURCES))
    }
}

fun TestOptions.testOptions() {
    unitTests.apply {
        all(KotlinClosure1<Any, Test>({
            (this as Test).also { testLogging() }
        }, this))
    }
}

fun LintOptions.lintOptions() {
    isAbortOnError = true
    isCheckAllWarnings = true
    isIgnoreWarnings = false
    isCheckReleaseBuilds = true
    isWarningsAsErrors = true
    lintConfig = file(Config.Lint.CONFIG_FILE_PATH)
    htmlReport = true
    xmlReport = true
    disable(*Config.Lint.disabledIssues)
}
