import io.petros.movies.Projects
import io.petros.movies.identifier
import io.petros.movies.libNamespace

plugins {
    id("custom.android.library")
    id("custom.android.library.compose")
    id("custom.detekt")
    id("custom.dependency.versions")
}

android {
    libNamespace(Projects.Implementation.Android.Core.CORE_COMPOSE)
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.androidx.compose.foundation.main)
    implementation(libs.androidx.compose.material)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                libs.androidx.compose.runtime.identifier(), // Ignore change to 'api' advice.
                libs.androidx.compose.material.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
