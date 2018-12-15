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
    implementation(Deps.kotlin)
    implementation(Deps.diDagger)
    kapt(Deps.diDaggerCompiler)
    implementation(Deps.rxJava)

    testImplementation(project(":test"))

    testImplementation(Deps.testJUnit)
    testImplementation(Deps.testAssertJ)
    testImplementation(Deps.mockMockitoKotlin, {
        exclude(ExcludedDeps.groupJetbrainsKotlin, ExcludedDeps.moduleKotlinReflect)
    })

    detektPlugins(Deps.pluginDetektFormatting)
}
