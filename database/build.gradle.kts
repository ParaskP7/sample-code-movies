import io.petros.movies.Projects
import io.petros.movies.disabledDatabaseIssues
import io.petros.movies.identifier
import io.petros.movies.libNamespace

plugins {
    id("custom.android.library")
    id("kotlin-kapt")
    id("custom.detekt")
    id("custom.dependency.versions")
}

android {
    libNamespace(Projects.Implementation.Android.Core.DATABASE)
    disabledDatabaseIssues()
}

kapt {
    strictMode = true
    showProcessorStats = true
    useBuildCache = true
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.UTILS))
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))

    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.androidx.sqlite)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.common)
    implementation(libs.koin.core.main)
    implementation(libs.koin.core.jvm)
}

dependencyAnalysis {
    issues {
        onIncorrectConfiguration {
            exclude(
                Projects.Implementation.Kotlin.DOMAIN, // Ignore change to 'api' advice.
                libs.kotlinx.coroutines.core.jvm.identifier(), // Ignore change to 'api' advice.
                libs.androidx.room.runtime.identifier(), // Ignore change to 'api' advice.
                libs.androidx.paging.common.identifier(), // Ignore change to 'api' advice.
                libs.koin.core.jvm.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
