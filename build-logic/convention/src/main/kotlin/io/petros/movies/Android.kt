@file:Suppress("TooManyFunctions")

package io.petros.movies

import com.android.build.api.dsl.AndroidSourceSet
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.ProguardFiles
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.tasks.testing.Test
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.util.*

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        commonSdk()
        buildFeatures()
        lint(project)
        compileOptions()
        kotlinOptions()
        testOptions()
        packagingOptions()
        sourceSets()
    }
}

internal fun Project.configureIgnoredVariants(
    androidComponentsExtension: AndroidComponentsExtension<*, *, *>,
) {
    androidComponentsExtension.apply {
        val ignoredVariants = findLocalProperty(Config.LocalProperties.Gradle.IGNORED_VARIANTS)?.split(Utils.COMMA)
        ignoredVariants?.forEach { ignoredVariant ->
            beforeVariants { variant ->
                if (variant.name == ignoredVariant) variant.enable = false
            }
        }
    }
}

/* NAMESPACE */

fun BaseAppModuleExtension.appNamespace() {
    namespace = Android.APPLICATION_ID
}

fun LibraryExtension.libNamespace(projectName: String) {
    namespace = Android.APPLICATION_ID + projectName.namespace()
}

fun String.namespace() = this
    .replace(Utils.COLON, Utils.DOT)
    .replace(Utils.DASH, Utils.DOT)

/* COMMON SDK */

fun CommonExtension<*, *, *, *, *>.commonSdk() {
    defaultConfig.minSdk = Android.Sdk.MIN
    compileSdk = Android.Sdk.COMPILE
}

/* DEFAULT CONFIG */

fun BaseAppModuleExtension.appDefaultConfig() {
    defaultConfig {
        applicationId = Android.APPLICATION_ID
        versionCode = Android.Version.CODE
        versionName = Android.Version.NAME
        testInstrumentationRunner = Android.DefaultConfig.Test.CUSTOM_INSTRUMENTATION_RUNNER
    }
}

/* BUILD TYPE */

fun BaseAppModuleExtension.appBuildTypes(project: Project) {
    buildTypes {
        val themoviedbApiKey = project.findLocalProperty(Config.LocalProperties.TheMoviesDb.API_KEY).asString()
        named(Android.BuildTypes.DEBUG) {
            isDebuggable = true
            buildConfigField(
                Config.BuildConfig.Field.STRING,
                Config.Gradle.THEMOVIEDB_API_KEY_CONST,
                themoviedbApiKey
            )
        }
        named(Android.BuildTypes.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                ProguardFiles.getDefaultProguardFile(
                    Android.BuildTypes.Proguard.ANDROID_TXT_FILE,
                    project.layout.buildDirectory
                ),
                Android.BuildTypes.Proguard.RULES_PRO_FILE
            )
            buildConfigField(
                Config.BuildConfig.Field.STRING,
                Config.Gradle.THEMOVIEDB_API_KEY_CONST,
                themoviedbApiKey
            )
        }
    }
}

fun Any?.asString() = "\"$this\""

/* BUILD FEATURES */

fun CommonExtension<*, *, *, *, *>.buildFeatures() {
    buildFeatures.apply {
        aidl = false
        compose = false
        buildConfig = false
        prefab = false
        renderScript = false
        resValues = false
        shaders = false
        viewBinding = false
        dataBinding {
            @Suppress("DEPRECATION")
            isEnabled = false
        }
    }
}

fun BaseAppModuleExtension.enableViewBindingAndBuildConfig() {
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

fun LibraryExtension.enableViewBinding() {
    buildFeatures {
        viewBinding = true
    }
}

fun LibraryExtension.enableBuildConfig() {
    buildFeatures {
        buildConfig = true
    }
}

/* LINT */

fun CommonExtension<*, *, *, *, *>.lint(project: Project) {
    lint {
        abortOnError = true
        checkAllWarnings = true
        ignoreWarnings = false
        checkReleaseBuilds = true
        warningsAsErrors = true
        lintConfig = project.file("${project.rootDir}/${Android.Lint.CONFIG_FILE_PATH}")
        htmlReport = true
        xmlReport = true
        disable += Android.Lint.disabledIssues
    }
}

fun LibraryExtension.disabledDatabaseIssues() {
    lint {
        disable += Android.Lint.disabledDatabaseIssues
    }
}

/* COMPILE OPTIONS */

fun CommonExtension<*, *, *, *, *>.compileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

/* KOTLIN OPTIONS */

fun CommonExtension<*, *, *, *, *>.kotlinOptions() {
    kotlinOptions { kotlinOptions() }
}

fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure(Android.Extension.KOTLIN_OPTIONS, block)
}

/* TEST OPTIONS */

@Suppress("UnstableApiUsage")
fun CommonExtension<*, *, *, *, *>.testOptions() {
    testOptions {
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
        unitTests.all { test: Test -> test.testLogging() }
    }
}

/* PACKAGING OPTIONS */

fun CommonExtension<*, *, *, *, *>.packagingOptions() {
    packagingOptions {
        jniLibs.useLegacyPackaging = false
        resources {
            excludes += mutableSetOf(
                Android.PackagingOption.Exclude.LICENCE,
                Android.PackagingOption.Exclude.LICENCE,
                Android.PackagingOption.Exclude.LICENCE_NOTICE,
                Android.PackagingOption.Exclude.AL,
                Android.PackagingOption.Exclude.LGPL,
                Android.PackagingOption.Exclude.KOTLIN_COROUTINES,
            )
        }
    }
}

/* SOURCE SETS */

fun CommonExtension<*, *, *, *, *>.sourceSets() {
    sourceSets {
        named(Sources.MAIN) { mainAndroidSourceSets() }
        named(Sources.TEST) { testAndroidSourceSets() }
        named(Sources.ANDROID_TEST) { androidTestAndroidSourceSets() }
    }
}

fun AndroidSourceSet.mainAndroidSourceSets() {
    java.setSrcDirs(
        arrayListOf(
            Sources.Main.KOTLIN,
        )
    )
    resources.setSrcDirs(
        arrayListOf(
            Sources.Main.RESOURCES,
        )
    )
}

fun AndroidSourceSet.testAndroidSourceSets() {
    java.setSrcDirs(
        arrayListOf(
            Sources.Test.KOTLIN,
            Sources.Robolectric.KOTLIN,
        )
    )
    resources.setSrcDirs(
        arrayListOf(
            Sources.Test.RESOURCES,
            Sources.Robolectric.RESOURCES,
        )
    )
}

fun AndroidSourceSet.androidTestAndroidSourceSets() {
    java.setSrcDirs(
        arrayListOf(
            Sources.Android.Test.KOTLIN,
        )
    )
    resources.setSrcDirs(
        arrayListOf(
            Sources.Android.Test.RESOURCES,
        )
    )
}

/* CONFIG */

object Android {

    const val APPLICATION_ID = "io.petros.movies"

    object Extension {

        const val KOTLIN_OPTIONS = "kotlinOptions"

    }

    object Sdk {

        const val MIN = 24
        const val TARGET = 33
        @Suppress("unused") const val TARGET_PREVIEW = "S"
        const val COMPILE = 33
        @Suppress("unused") const val COMPILE_PREVIEW = "android-S"

    }

    object Version {

        private const val MAJOR = 1
        private const val MINOR = 0
        private const val PATCH = 0
        private const val BUILD = 0

        const val CODE = MAJOR * 10_000 + MINOR * 1_000 + PATCH * 100 + BUILD
        const val NAME = "$MAJOR.$MINOR.$PATCH"

    }

    object Lint {

        const val CONFIG_FILE_PATH = "${Config.QUALITY_DIRECTORY}/${Files.Xml.LINT}"

        val disabledIssues = arrayOf(
            "UnusedIds", // Because of UAST
            "InvalidPackage",
            "DialogFragmentCallbacksDetector", // From Android Gradle Plugin version 7.4.0-alpha02 and onwards
            "UnknownIssueId", // From Android Gradle Plugin version 7.4.0-alpha02 and onwards
            "GradleDependency",
        )

        val disabledDatabaseIssues = arrayOf(
            "RestrictedApi", // From Android Gradle Plugin version 8.0.0-alpha03 and onwards
            "SyntheticAccessor", // From Android Gradle Plugin version 8.0.0-alpha03 and onwards
            "UnknownNullness", // From Android Gradle Plugin version 8.0.0-alpha03 and onwards
        )

        object Files {

            object Xml {

                const val LINT = "lint.xml"

            }

        }

    }

    object DefaultConfig {

        object Test {

            @Suppress("unused")
            const val DEFAULT_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
            const val CUSTOM_INSTRUMENTATION_RUNNER = "io.petros.movies.app.runner.CustomAndroidJunitRunner"

        }

    }

    object BuildTypes {

        const val DEBUG = "debug"
        const val RELEASE = "release"

        object Proguard {

            const val ANDROID_TXT_FILE = "proguard-android.txt"
            const val RULES_PRO_FILE = "proguard-rules.pro"

        }

    }

    object PackagingOption {

        object Exclude {

            private const val META_INF_DIR = "META-INF"

            const val LICENCE = "$META_INF_DIR/LICENSE.md"
            const val LICENCE_NOTICE = "$META_INF_DIR/LICENSE-notice.md"

            const val AL = "$META_INF_DIR/AL2.0"
            const val LGPL = "$META_INF_DIR/LGPL2.1"

            const val KOTLIN_COROUTINES = "$META_INF_DIR/kotlinx-coroutines-core.kotlin_module"

        }

    }

}
