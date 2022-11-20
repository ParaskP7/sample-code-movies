import io.petros.movies.Projects
import io.petros.movies.enableViewBinding
import io.petros.movies.identifier
import io.petros.movies.libNamespace

plugins {
    id("custom.android.library")
    id("custom.detekt")
    id("custom.dependency.versions")
    id("custom.jacoco")
}

android {
    libNamespace(Projects.Implementation.Android.Feature.MOVIE_DETAILS)
    enableViewBinding()
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.UTILS))
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Android.Core.CORE))

    implementation(libs.kotlinx.coroutines.core.main)
    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.material)
    implementation(libs.androidx.fragment.main)
    implementation(libs.androidx.constraint.layout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.livedata.core)
    implementation(libs.androidx.lifecycle.view.model.main)
    implementation(libs.androidx.lifecycle.view.model.ktx)
    implementation(libs.koin.core.main)
    implementation(libs.koin.core.jvm)
    implementation(libs.koin.android)
    implementation(libs.timber)

    testImplementation(project(Projects.Implementation.Android.Core.ANDROID_UTILS))
    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))

    testImplementation(libs.kotlinx.coroutines.test.main)
    testImplementation(libs.kotlinx.coroutines.test.jvm)
    testImplementation(libs.junit4)
    testRuntimeOnly(libs.kotlin.reflect)
    testImplementation(libs.strikt)
    testImplementation(libs.mockk.main)
    testImplementation(libs.mockk.dsl.jvm)
    testImplementation(libs.androidx.arch.core.testing)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Projects.Implementation.Android.Core.CORE, // Ignore change to 'api' advice.
                libs.material.identifier(), // Ignore change to 'api' advice.
                libs.androidx.lifecycle.livedata.core.identifier(), // Ignore change to 'testImpl' advice.
                libs.koin.core.jvm.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
