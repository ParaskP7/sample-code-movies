/* PLUGINS */

plugins {
    id(PluginIds.Kotlin.KOTLIN)
    id(PluginIds.Kotlin.KAPT)
    id(PluginIds.Quality.DETEKT)
    id(PluginIds.Dependency.VERSIONS)
}

/* DEPENDENCIES */

dependencies {
    implementation(project(Project.Implementation.DOMAIN))

    implementation(Deps.Kotlin.KOTLIN)
    implementation(Deps.Rx.RX_JAVA)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
