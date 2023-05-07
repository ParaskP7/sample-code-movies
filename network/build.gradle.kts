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
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))

    implementation(libs.koin.core.main)
    implementation(libs.koin.core.jvm)
    implementation(libs.okhttp.main)
    implementation(libs.okhttp.jvm)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)
    implementation(libs.retrofit.main)
    implementation(libs.retrofit.gson)

    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))

    testImplementation(libs.kotlinx.coroutines.core.jvm)
    testImplementation(libs.kotlinx.coroutines.test.main)
    testImplementation(libs.kotlinx.coroutines.test.jvm)
    testImplementation(libs.junit4)
    testRuntimeOnly(libs.kotlin.reflect)
    testImplementation(libs.okhttp.mock.web.server)
    testImplementation(libs.strikt)
    testImplementation(libs.mockk.main)
    testImplementation(libs.mockk.dsl.jvm)
}

@Suppress("ForbiddenComment")
dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                "() -> java.io.File?", // TODO: Figure out why this is reported as unused.
            )
        }
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                libs.koin.core.jvm.identifier(), // Ignore change to 'api' advice.
                libs.retrofit.main.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
