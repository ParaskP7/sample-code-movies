/* PLUGINS */

plugins {
    id(PluginIds.Kotlin.KOTLIN)
    id(PluginIds.Kotlin.KAPT)
    id(PluginIds.Dependency.VERSIONS)
    id(PluginIds.Quality.DETEKT)
}

/* KOTLIN */

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

/* DEPENDENCIES */

dependencies {
    implementation(project(Project.Implementation.DOMAIN))

    implementation(Deps.Kotlin.KOTLIN)
    implementation(Deps.Rx.RX_JAVA)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
