import io.petros.movies.Projects
import io.petros.movies.identifier
import io.petros.movies.libNamespace

plugins {
    id("custom.android.library")
    id("custom.detekt")
    id("custom.dependency.versions")
}

android {
    libNamespace(Projects.Implementation.Android.Lib.PICKER)
}

dependencies {
    implementation(project(Projects.Implementation.Android.Core.CORE))

    implementation(libs.androidx.lifecycle.view.model.main)
    implementation(libs.androidx.app.compat.main)
    implementation(libs.androidx.app.compat.resources)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.fragment.main)
    implementation(libs.timber)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                libs.androidx.fragment.main.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
