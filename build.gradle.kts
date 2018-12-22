import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.detekt

import com.android.build.gradle.AppPlugin as AndroidApplicationPlugin
import com.android.build.gradle.LibraryPlugin as AndroidLibraryPlugin
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.LibraryExtension

import com.github.benmanes.gradle.versions.VersionsPlugin
import com.getkeepsafe.dexcount.DexMethodCountPlugin as DexcountPlugin
import com.getkeepsafe.dexcount.DexMethodCountExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

/* EXTENSION FUNCTIONS */

fun Project.java(configure: JavaPluginExtension.() -> Unit) =
    extensions.configure(JavaPluginExtension::class.java, configure)

fun Project.kapt(configure: KaptExtension.() -> Unit) =
    extensions.configure(KaptExtension::class.java, configure)

fun Project.dexcount(configure: DexMethodCountExtension.() -> Unit) =
    extensions.configure(DexMethodCountExtension::class.java, configure)

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

subprojects {
    plugins.withType(KotlinPluginWrapper::class) {
        task("logKotlinPlugin") { println("<<<RUNNING WITH KOTLIN PLUGIN>>>") }
        java {
            sourceCompatibility = Java.version
            targetCompatibility = Java.version
        }
        tasks.getByName<Test>(Tasks.TEST) {
            testLogging {
                events(*Logs.eventsKts)
                setExceptionFormat(Logs.EXCEPTION_FORMAT)
            }
        }
    }
    plugins.withType(Kapt3GradleSubplugin::class) {
        task("logKotlinKaptPlugin") { println("<<<RUNNING WITH KOTLIN KAPT PLUGIN>>>") }
        kapt {
            useBuildCache = true
        }
    }
    plugins.withType(AndroidLibraryPlugin::class) {
        task("logAndroidLibraryPlugin") { println("<<<RUNNING WITH ANDROID LIBRARY PLUGIN>>>") }
        apply(Config.Gradle.ANDROID)
        apply(Config.Gradle.LINT)
    }
    plugins.withType(AndroidApplicationPlugin::class) {
        task("logAndroidApplicationPlugin") { println("<<<RUNNING WITH ANDROID APPLICATION PLUGIN>>>") }
        apply(Config.Gradle.ANDROID)
        apply(Config.Gradle.LINT)
    }
    plugins.withType(DexcountPlugin::class) {
        task("logDexcountPlugin") { println("<<<RUNNING WITH DEXCOUNT PLUGIN>>>") }
        dexcount {
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
    }
    plugins.withType(DetektPlugin::class) {
        task("logDetektPlugin") { println("<<<RUNNING WITH DETEKT PLUGIN>>>") }
        detekt {
            toolVersion = Versions.Plugin.DETEKT
            config = files(Config.Detekt.CONFIG_FILE_PATH)
            filters = Config.Detekt.FILTERS
            disableDefaultRuleSets = false
            parallel = true
        }
    }
    plugins.withType(VersionsPlugin::class) {
        task("logVersionsPlugin") { println("<<<RUNNING WITH VERSIONS PLUGIN>>>") }
        apply(Config.Gradle.DEPENDENCY_UPDATES)
    }
}
