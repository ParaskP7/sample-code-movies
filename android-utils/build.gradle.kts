import io.petros.movies.Projects
import io.petros.movies.enableBuildConfig
import io.petros.movies.identifier
import io.petros.movies.libNamespace

plugins {
    id("custom.android.library")
    id("custom.detekt")
    id("custom.dependency.versions")
}

android {
    libNamespace(Projects.Implementation.Android.Core.ANDROID_UTILS)
    enableBuildConfig()
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.UTILS))

    implementation(libs.androidx.fragment.main)
    implementation(libs.androidx.lifecycle.common.main)
    implementation(libs.androidx.lifecycle.livedata.core)
    implementation(libs.timber)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                libs.androidx.fragment.main.identifier(), // Ignore change to 'api' advice.
                libs.androidx.lifecycle.common.main.identifier(), // Ignore change to 'api' advice.
                libs.androidx.lifecycle.livedata.core.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
