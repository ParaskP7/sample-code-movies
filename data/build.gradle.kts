@file:Suppress("InvalidPackageDeclaration", "ForbiddenComment")

import com.android.build.gradle.internal.dsl.BuildType
import io.petros.movies.config.android.Android
import io.petros.movies.config.android.BuildConfig
import io.petros.movies.config.android.Keys
import io.petros.movies.config.deps.Deps
import io.petros.movies.config.utils.asString
import java.io.FileInputStream
import java.util.*

/* PLUGINS */

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    // id(Plugins.Id.Test.Android.J_UNIT_5) // FIXME: Failed to notify project evaluation listener.
    id(Plugins.Id.Dependency.VERSIONS)
    id(Plugins.Id.Test.JACOCO)
    id(Plugins.Id.Test.COVERAGE)
}

/* ANDROID */

android {
    buildTypes { buildTypes() }
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Deps.Project.Implementation.Kotlin.DOMAIN))
    implementation(project(Deps.Project.Implementation.Kotlin.NETWORK))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
    implementation(Deps.Di.Koin.Kotlin.CORE)

    testImplementation(project(Deps.Project.TestImplementation.Kotlin.TEST))

    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT_5)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_5)
    testImplementation(Deps.Test.Assert.STRIKT)
    testImplementation(Deps.Test.Mock.MOCK_K)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}

/* *********************************************************************************************************************** */

/* CONFIGURATION EXTENSION FUNCTIONS */

// TODO: Think about how to add this logic back
fun NamedDomainObjectContainer<BuildType>.buildTypes() {
    val themoviedbApiProperties = Properties()
    themoviedbApiProperties.load(FileInputStream(file(Keys.TheMoviesDb.Config.API_FILE_PATH)))
    val themoviedbApiKey = themoviedbApiProperties[Keys.TheMoviesDb.Property.API_KEY].asString()
    named(Android.BuildTypes.DEBUG) {
        buildConfigField(BuildConfig.Field.STRING, Keys.TheMoviesDb.Config.API_KEY_CONST, themoviedbApiKey)
    }
    named(Android.BuildTypes.RELEASE) {
        buildConfigField(BuildConfig.Field.STRING, Keys.TheMoviesDb.Config.API_KEY_CONST, themoviedbApiKey)
    }
}
