import io.petros.movies.Projects
import io.petros.movies.identifier
import io.petros.movies.libNamespace

plugins {
    id("custom.android.library")
    id("custom.detekt")
    id("custom.dependency.versions")
    id("custom.jacoco")
}

android {
    libNamespace(Projects.Implementation.Android.Core.DATA)
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Kotlin.NETWORK))
    implementation(project(Projects.Implementation.Android.Core.DATASTORE))
    implementation(project(Projects.Implementation.Android.Core.DATABASE))

    implementation(libs.kotlinx.coroutines.core.main)
    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences.core)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.paging.common)
    implementation(libs.koin.core.main)
    implementation(libs.koin.core.jvm)
    implementation(libs.timber)

    testImplementation(project(Projects.TestImplementation.Kotlin.TEST))
    testImplementation(project(Projects.TestImplementation.Android.ANDROID_TEST))

    testImplementation(libs.kotlinx.coroutines.test.main)
    testImplementation(libs.kotlinx.coroutines.test.jvm)
    testImplementation(libs.junit4)
    testRuntimeOnly(libs.kotlin.reflect)
    testImplementation(libs.strikt)
    testImplementation(libs.mockk.main)
    testImplementation(libs.mockk.dsl.jvm)
    testImplementation(libs.robolectric.main)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                libs.robolectric.main.identifier(), // Ignore remove advise. Required for tests.
            )
        }
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                Projects.Implementation.Kotlin.NETWORK, // Ignore change to 'api' advice.
                Projects.Implementation.Android.Core.DATASTORE, // Ignore change to 'api' advice.
                Projects.Implementation.Android.Core.DATABASE, // Ignore change to 'api' advice.
                libs.androidx.paging.common.identifier(), // Ignore change to 'api' advice.
                libs.koin.core.jvm.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
