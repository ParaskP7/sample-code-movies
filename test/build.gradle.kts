plugins {
    id("kotlin")
    id("kotlin-kapt")
    id("com.github.ben-manes.versions")
    id("io.gitlab.arturbosch.detekt")
}

apply(Config.GRADLE_DETEKT)
apply(Config.GRADLE_DEPENDENCY_UPDATES)

java {
    sourceCompatibility = Java.version
    targetCompatibility = Java.version
}

tasks.getByName<Test>("test") {
    testLogging {
        events(*Logs.testLogEvents)
        setExceptionFormat(Logs.TEST_LOGGING_EXCEPTION_FORMAT)
    }
}

kapt {
    useBuildCache = true
}

dependencies {
    implementation(project(":domain"))

    implementation(Deps.kotlin)
    implementation(Deps.rxJava)

    detektPlugins(Deps.pluginDetektFormatting)
}
