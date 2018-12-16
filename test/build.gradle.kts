plugins {
    id(PluginIds.KOTLIN)
    id(PluginIds.KOTLIN_KAPT)
    id(PluginIds.VERSIONS)
    id(PluginIds.DETEKT)
}

apply(Config.GRADLE_DETEKT)
apply(Config.GRADLE_DEPENDENCY_UPDATES)

java {
    sourceCompatibility = Java.version
    targetCompatibility = Java.version
}

tasks.getByName<Test>(Tasks.TEST) {
    testLogging {
        events(*Logs.testLogEvents)
        setExceptionFormat(Logs.TEST_LOGGING_EXCEPTION_FORMAT)
    }
}

kapt {
    useBuildCache = true
}

dependencies {
    implementation(project(Project.DOMAIN))

    implementation(Deps.kotlin)
    implementation(Deps.rxJava)

    detektPlugins(Deps.pluginDetektFormatting)
}
