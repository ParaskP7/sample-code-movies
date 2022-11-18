import io.petros.movies.Projects
import io.petros.movies.identifier
import io.petros.movies.libNamespace

plugins {
    id("custom.android.library")
    id("custom.detekt")
    id("custom.dependency.versions")
}

android {
    libNamespace(Projects.Implementation.Android.Core.DATASTORE)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences.core)
    implementation(libs.androidx.datastore.preferences.main)
    implementation(libs.koin.core.main)
    implementation(libs.koin.core.jvm)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                libs.androidx.datastore.core.identifier(), // Ignore change to 'api' advice.
                libs.androidx.datastore.preferences.core.identifier(), // Ignore change to 'api' advice.
                libs.koin.core.jvm.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
