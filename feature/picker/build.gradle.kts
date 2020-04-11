@file:Suppress("InvalidPackageDeclaration")

/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Quality.DETEKT)
    id(PluginIds.Dependency.VERSIONS)
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Deps.Project.Implementation.Android.Core.CORE))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Android.Core.APP_COMPAT)
    api(Deps.Util.MONTH_YEAR_PICKER) // Usage of api is required because of 'MovieActivity', which cannot access it.

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
