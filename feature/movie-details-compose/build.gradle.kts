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
    libNamespace(Projects.Implementation.Android.Feature.MOVIE_DETAILS_COMPOSE)
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Android.Core.CORE_COMPOSE))

    implementation(libs.kotlinx.coroutines.core.main)
    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.main)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation.main)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.constraint.layout)
    implementation(libs.coil.compose)
    implementation(libs.androidx.fragment.main)
    implementation(libs.androidx.lifecycle.view.model.main)
    implementation(libs.androidx.lifecycle.view.model.ktx)
    implementation(libs.koin.core.main)
    implementation(libs.koin.core.jvm)
    implementation(libs.koin.android)
    implementation(libs.timber)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                libs.androidx.compose.runtime.identifier(), // Ignore change to 'api' advice.
                libs.androidx.fragment.main.identifier(), // Ignore change to 'api' advice.
                libs.androidx.lifecycle.view.model.main.identifier(), // Ignore change to 'api' advice.
                libs.koin.core.jvm.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
