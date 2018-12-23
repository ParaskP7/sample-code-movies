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
    id(PluginIds.Dependency.VERSIONS)
    id(PluginIds.Quality.DETEKT)
}

/* ANDROID */

android {
    buildTypes { buildTypes() }
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Project.Implementation.DOMAIN))

    implementation(Deps.Kotlin.KOTLIN)
    implementation(Deps.Android.APP_COMPAT)
    implementation(Deps.Di.DAGGER)
    kapt(Deps.Di.DAGGER_COMPILER)
    implementation(Deps.Di.DAGGER_ANDROID)
    kapt(Deps.Di.DAGGER_ANDROID_PROCESSOR)
    implementation(Deps.Rx.RX_JAVA)
    implementation(Deps.Rx.RX_ANDROID)
    implementation(Deps.Net.GSON)
    implementation(Deps.Net.OK_HTTP_LOGGING)
    implementation(Deps.Net.Rest.RETROFIT)
    implementation(Deps.Net.Rest.RETROFIT_GSON)
    implementation(Deps.Net.Rest.RETROFIT_RX)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Project.TestImplementation.TEST))

    testImplementation(Deps.Test.J_UNIT)
    testImplementation(Deps.Test.ASSERT_J)
    testImplementation(Deps.Mock.MOCKITO_KOTLIN, {
        exclude(ExcludedDeps.Group.Jetbrains.KOTLIN, ExcludedDeps.Module.Kotlin.REFLECT)
    })

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
