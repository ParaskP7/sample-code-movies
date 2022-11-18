import io.petros.movies.Projects
import io.petros.movies.identifier
import io.petros.movies.libNamespace

plugins {
    id("custom.android.library")
    id("custom.detekt")
    id("custom.dependency.versions")
}

android {
    libNamespace(Projects.TestImplementation.Android.ANDROID_TEST)
}

dependencies {
    implementation(libs.kotlinx.coroutines.test.main)
    implementation(libs.material)
    implementation(libs.androidx.test.core.main)
    implementation(libs.junit4)
    runtimeOnly(libs.kotlin.reflect)
    implementation(libs.robolectric.main)
    implementation(libs.robolectric.annotations)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                libs.material.identifier(), // Ignore remove advise. Required for theme purposes.
                libs.junit4.identifier(), // Ignore remove advise. Required because of Robolectric.
            )
        }
        onIncorrectConfiguration {
            exclude(
                libs.robolectric.main.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
