import io.petros.movies.Projects
import io.petros.movies.enableViewBinding
import io.petros.movies.identifier
import io.petros.movies.libNamespace

plugins {
    id("custom.android.library")
    id("custom.detekt")
    id("custom.dependency.versions")
}

android {
    libNamespace(Projects.Implementation.Android.Core.CORE)
    enableViewBinding()
}

dependencies {
    implementation(project(Projects.Implementation.Android.Core.ANDROID_UTILS))

    implementation(libs.material)
    implementation(libs.androidx.app.compat.main)
    implementation(libs.androidx.fragment.main)
    implementation(libs.androidx.lifecycle.common.main)
    implementation(libs.androidx.lifecycle.common.java8)
    implementation(libs.androidx.lifecycle.livedata.core)
    implementation(libs.androidx.lifecycle.view.model.main)
    implementation(libs.glide)
    implementation(libs.timber)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Android.Core.ANDROID_UTILS, // Ignore change to 'api' advice.
                libs.material.identifier(), // Ignore change to 'api' advice.
                libs.androidx.app.compat.main.identifier(), // Ignore change to 'api' advice.
                libs.androidx.fragment.main.identifier(), // Ignore change to 'api' advice.
                libs.androidx.lifecycle.livedata.core.identifier(), // Ignore change to 'api' advice.
                libs.androidx.lifecycle.view.model.main.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
