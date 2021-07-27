@file:Suppress("InvalidPackageDeclaration")

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import io.petros.movies.config.Config
import io.petros.movies.config.android.Android
import io.petros.movies.config.android.App
import io.petros.movies.config.android.BuildConfig
import io.petros.movies.config.android.LocalProperties
import io.petros.movies.config.android.findLocalProperty
import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Projects
import io.petros.movies.config.deps.identifier
import io.petros.movies.config.dirs.Files
import io.petros.movies.config.utils.asString
import io.petros.movies.config.utils.logApiKey

plugins {
    id(Plugins.Id.Android.APPLICATION)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Module.GRAPH_ASSERT) version Plugins.Version.MODULE_GRAPH_ASSERT
}

@Suppress("SpreadOperator")
android {
    defaultConfig { defaultConfig() }
    buildTypes { buildTypes() }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    lintOptions {
        disable(*Config.Lint.disabledAppIssues)
    }
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Android.Core.ANDROID_UTILS))
    implementation(project(Projects.Implementation.Android.Core.DATA))
    implementation(project(Projects.Implementation.Android.Core.CORE))
    implementation(project(Projects.Implementation.Android.Feature.MOVIES))
    implementation(project(Projects.Implementation.Android.Feature.MOVIE_DETAILS))
    implementation(project(Projects.Implementation.Android.Feature.MOVIE_DETAILS_COMPOSE))

    debugImplementation(Deps.LeakCanary.LEAK_CANARY)
    implementation(Deps.LeakCanary.PLUMBER)

    implementation(Deps.Kotlin.Coroutines.CORE_JVM)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.CONSTRAINT_LAYOUT)
    implementation(Deps.Android.Core.DRAWER_LAYOUT)
    implementation(Deps.Android.Arch.Lifecycle.COMMON)
    implementation(Deps.Android.Arch.Lifecycle.RUNTIME_KTX)
    implementation(Deps.Android.Arch.Lifecycle.LIVE_DATA_CORE)
    implementation(Deps.Android.Arch.Lifecycle.PROCESS)
    implementation(Deps.Android.Arch.Navigation.RUNTIME)
    implementation(Deps.Android.Arch.Navigation.RUNTIME_KTX)
    implementation(Deps.Android.Arch.Navigation.UI)
    implementation(Deps.Android.Arch.Navigation.UI_KTX)
    implementation(Deps.Android.Arch.Navigation.FRAGMENT_KTX)
    implementation(Deps.Android.Arch.CustomView.CUSTOM_VIEW)
    implementation(Deps.Di.Koin.Kotlin.CORE)
    implementation(Deps.Di.Koin.Kotlin.CORE_JVM)
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Log.TIMBER)

    androidTestImplementation(project(Projects.TestImplementation.Kotlin.TEST))

    androidTestImplementation(Deps.Android.Test.CORE)
    androidTestImplementation(Deps.Android.Test.CORE_KTX)
    androidTestImplementation(Deps.Android.Test.J_UNIT)
    androidTestImplementation(Deps.Android.Test.RUNNER)
    androidTestImplementation(Deps.Android.Test.RULES)
    androidTestImplementation(Deps.Android.Test.Espresso.CORE) {
        exclude(Deps.Android.Test.Espresso.Exclude.HAMCREST)
    }
    androidTestImplementation(Deps.Android.Test.Espresso.CONTRIB) {
        exclude(Deps.Android.Test.Espresso.Exclude.HAMCREST)
    }
    androidTestImplementation(Deps.Test.Integration.MOCK_WEB_SERVER)
    androidTestImplementation(Deps.Test.Hamcrest.ALL)
    androidTestImplementation(Deps.Test.Assert.STRIKT) { exclude(Deps.Test.Assert.Exclude.KOTLIN) }

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                Deps.Android.Arch.Navigation.FRAGMENT_KTX.identifier(), // Ignore remove advise. Required for nav.
            )
        }
        onIncorrectConfiguration {
            exclude(
                Deps.Kotlin.Core.KOTLIN.identifier(), // Ignore change to 'implementation' advice.
            )
        }
    }
}

moduleGraphAssert {
    maxHeight = Config.Module.GraphAssert.MAX_HEIGHT
    moduleLayers = Config.Module.GraphAssert.moduleLayers
    moduleLayersExclude = Config.Module.GraphAssert.moduleLayersExclude
    restricted = Config.Module.GraphAssert.restricted
    configurations = Config.Module.GraphAssert.configurations
}

/* CONFIGURATION EXTENSION FUNCTIONS */

fun ApplicationDefaultConfig.defaultConfig() {
    applicationId = App.APPLICATION_ID
    versionCode = App.Version.CODE
    versionName = App.Version.NAME
    testInstrumentationRunner = Android.DefaultConfig.Test.CUSTOM_INSTRUMENTATION_RUNNER
}

fun NamedDomainObjectContainer<ApplicationBuildType>.buildTypes() {
    val themoviedbApiKey = findLocalProperty(LocalProperties.TheMoviesDb.API_KEY).asString()
    logApiKey(themoviedbApiKey)
    named(Android.BuildTypes.DEBUG) {
        isDebuggable = true
        buildConfigField(BuildConfig.Field.STRING, Config.Gradle.THEMOVIEDB_API_KEY_CONST, themoviedbApiKey)
    }
    named(Android.BuildTypes.RELEASE) {
        isMinifyEnabled = false
        proguardFiles(
            getDefaultProguardFile(Files.Txt.PROGUARD_ANDROID, layout.buildDirectory),
            Files.Pro.PROGUARD_RULES
        )
        buildConfigField(BuildConfig.Field.STRING, Config.Gradle.THEMOVIEDB_API_KEY_CONST, themoviedbApiKey)
    }
}
