import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.detekt

import com.android.build.gradle.BasePlugin as AndroidPlugin
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.LibraryExtension

import com.github.benmanes.gradle.versions.VersionsPlugin
import com.getkeepsafe.dexcount.DexMethodCountPlugin as DexcountPlugin
import com.getkeepsafe.dexcount.DexMethodCountExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformCommonPlugin
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

/* EXTENSION FUNCTIONS */

fun Project.dexcount(configure: DexMethodCountExtension.() -> Unit) =
    extensions.configure(DexMethodCountExtension::class.java, configure)

fun Project.java(configure: JavaPluginExtension.() -> Unit) =
    extensions.configure(JavaPluginExtension::class.java, configure)

fun Project.kapt(configure: KaptExtension.() -> Unit) =
    extensions.configure(KaptExtension::class.java, configure)

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
    plugins.withType(KotlinPlatformCommonPlugin::class) {
        java {
            sourceCompatibility = Java.version
            targetCompatibility = Java.version
        }
        kapt {
            useBuildCache = true
        }
    }
    plugins.withType(AndroidPlugin::class) {
        apply(Config.Gradle.ANDROID)
        apply(Config.Gradle.LINT)
    }
    plugins.withType(DexcountPlugin::class) {
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
        detekt {
            toolVersion = Versions.Plugin.DETEKT
            config = files(Config.Detekt.CONFIG_FILE_PATH)
            filters = Config.Detekt.FILTERS
            disableDefaultRuleSets = false
            parallel = true
        }
    }
    plugins.withType(VersionsPlugin::class) {
        apply(Config.Gradle.DEPENDENCY_UPDATES)
    }
}
