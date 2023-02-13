import io.petros.movies.Projects
import io.petros.movies.appBuildTypes
import io.petros.movies.appDefaultConfig
import io.petros.movies.appNamespace
import io.petros.movies.enableViewBindingAndBuildConfig
import io.petros.movies.identifier

plugins {
    id("custom.android.application")
    id("custom.detekt")
    id("custom.dependency.versions")
    id("custom.modules.graph.assert")
}

android {
    appNamespace()
    appDefaultConfig()
    appBuildTypes(project)
    enableViewBindingAndBuildConfig()
}

dependencies {
    implementation(project(Projects.Implementation.Kotlin.DOMAIN))
    implementation(project(Projects.Implementation.Android.Core.ANDROID_UTILS))
    implementation(project(Projects.Implementation.Android.Core.DATA))
    implementation(project(Projects.Implementation.Android.Core.CORE))
    implementation(project(Projects.Implementation.Android.Feature.MOVIES))
    implementation(project(Projects.Implementation.Android.Feature.MOVIE_DETAILS))
    implementation(project(Projects.Implementation.Android.Feature.MOVIE_DETAILS_COMPOSE))

    debugImplementation(libs.leak.canary)

    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.material)
    implementation(libs.androidx.app.compat.main)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.constraint.layout)
    implementation(libs.androidx.drawer.layout)
    implementation(libs.androidx.core.main)
    implementation(libs.androidx.lifecycle.common.main)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.core)
    implementation(libs.androidx.lifecycle.view.model.main)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.navigation.runtime)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.custom.view)
    implementation(libs.koin.core.main)
    implementation(libs.koin.core.jvm)
    implementation(libs.koin.android)
    implementation(libs.timber)

    androidTestImplementation(project(Projects.Implementation.Kotlin.UTILS))
    androidTestImplementation(project(Projects.TestImplementation.Kotlin.TEST))

    androidTestImplementation(libs.androidx.recycler.view)
    androidTestImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.core.main)
    androidTestImplementation(libs.androidx.test.core.ktx)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.espresso.contrib)
    androidTestImplementation(libs.okhttp.mock.web.server)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                libs.leak.canary.identifier(), // Ignore remove advise. Required for nav.
                libs.androidx.navigation.fragment.ktx.identifier(), // Ignore remove advise. Required for nav.
            )
        }
        onUsedTransitiveDependencies {
            exclude(
                libs.hamcrest.identifier(), // Ignore remove advise. Required for espresso.
            )
        }
    }
}
