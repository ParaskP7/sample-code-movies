import io.petros.movies.Projects
import io.petros.movies.identifier

plugins {
    id("custom.kotlin")
    id("custom.detekt")
    id("custom.dependency.versions")
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))

    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.kotlinx.coroutines.test.main)
    implementation(libs.kotlinx.coroutines.test.jvm)
    implementation(libs.okhttp.main)
    implementation(libs.okhttp.jvm)
    implementation(libs.gson)
    implementation(libs.retrofit.main)
    implementation(libs.retrofit.gson)
    implementation(libs.junit4)
    runtimeOnly(libs.kotlin.reflect)
    implementation(libs.okhttp.mock.web.server)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                libs.junit4.identifier(), // Ignore change to 'api' advice.
                libs.okhttp.mock.web.server.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
