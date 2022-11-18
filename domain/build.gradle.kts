import io.petros.movies.Projects
import io.petros.movies.identifier

plugins {
    id("custom.kotlin")
    id("custom.detekt")
    id("custom.dependency.versions")
    id("custom.jacoco")
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.UTILS))

    implementation(libs.kotlinx.coroutines.core.main)
    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.androidx.paging.common)
    implementation(libs.koin.core.main)
    implementation(libs.koin.core.jvm)

    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))

    testImplementation(libs.kotlinx.coroutines.test.main)
    testImplementation(libs.kotlinx.coroutines.test.jvm)
    testImplementation(libs.junit4)
    testRuntimeOnly(libs.kotlin.reflect)
    testImplementation(libs.strikt) { exclude("org.jetbrains.kotlin") }
    testImplementation(libs.mockk.main)
    testImplementation(libs.mockk.dsl.jvm)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                libs.koin.core.jvm.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
