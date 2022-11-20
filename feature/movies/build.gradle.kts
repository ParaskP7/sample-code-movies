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
    libNamespace(Projects.Implementation.Android.Feature.MOVIES)
    enableViewBinding()
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.UTILS))
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Android.Core.ANDROID_UTILS))
    implementation(project(Projects.Implementation.Android.Core.CORE))
    implementation(project(Projects.Implementation.Android.Lib.PICKER))

    implementation(libs.kotlinx.coroutines.core.main)
    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.material)
    implementation(libs.androidx.app.compat.main)
    implementation(libs.androidx.fragment.main)
    implementation(libs.androidx.recycler.view)
    implementation(libs.androidx.constraint.layout)
    implementation(libs.androidx.coordinator.layout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.common.main)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.core)
    implementation(libs.androidx.lifecycle.view.model.main)
    implementation(libs.androidx.lifecycle.view.model.ktx)
    implementation(libs.androidx.navigation.common)
    implementation(libs.androidx.navigation.runtime)
    implementation(libs.androidx.navigation.fragment.main)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.koin.core.main)
    implementation(libs.koin.core.jvm)
    implementation(libs.koin.android)
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
    testImplementation(libs.androidx.arch.core.testing)
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
                Projects.Implementation.Android.Core.CORE, // Ignore change to 'api' advice.
                libs.material.identifier(), // Ignore change to 'api' advice.
                libs.androidx.constraint.layout.identifier(), // Ignore change to 'api' advice.
                libs.androidx.coordinator.layout.identifier(), // Ignore change to 'api' advice.
                libs.androidx.recycler.view.identifier(), // Ignore change to 'api' advice.
                libs.androidx.paging.common.identifier(), // Ignore change to 'api' advice.
                libs.androidx.paging.runtime.identifier(), // Ignore change to 'api' advice.
                libs.koin.core.jvm.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
