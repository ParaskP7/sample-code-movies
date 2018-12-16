plugins {
    id("kotlin")
    id("kotlin-kapt")
    id("com.github.ben-manes.versions")
    id("io.gitlab.arturbosch.detekt")
}

apply("../config/gradle/quality/detekt.gradle")
apply("../config/gradle/dependencies/dependency_updates.gradle")

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
