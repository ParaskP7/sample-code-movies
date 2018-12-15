plugins {
    id("kotlin")
    id("kotlin-kapt")
    id("com.github.ben-manes.versions")
    id("io.gitlab.arturbosch.detekt")
}

apply("../config/gradle/lang/kotlin.gradle")
apply("../config/gradle/quality/detekt.gradle")
apply("../config/gradle/dependencies/dependency_updates.gradle")

dependencies {
    implementation(project(":domain"))

    implementation(Deps.kotlin)
    implementation(Deps.rxJava)

    detektPlugins(Deps.pluginDetektFormatting)
}
