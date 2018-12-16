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
    implementation(Deps.Kotlin.KOTLIN)
    implementation(Deps.Di.DAGGER)
    kapt(Deps.Di.DAGGER_COMPILER)
    implementation(Deps.Rx.RX_JAVA)

    testImplementation(project(Project.TEST))

    testImplementation(Deps.Test.J_UNIT)
    testImplementation(Deps.Test.ASSERT_J)
    testImplementation(Deps.Mock.MOCKITO_KOTLIN, {
        exclude(ExcludedDeps.Group.Jetbrains.KOTLIN, ExcludedDeps.Module.Kotlin.REFLECT)
    })

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
