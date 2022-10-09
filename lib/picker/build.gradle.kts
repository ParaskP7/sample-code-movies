@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.android.App
import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.Projects
import io.petros.movies.config.deps.identifier
import io.petros.movies.config.deps.namespace

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

android {
    namespace = App.APPLICATION_ID + Projects.Implementation.Android.Lib.PICKER.namespace()
}

dependencies {
    implementation(project(Projects.Implementation.Android.Core.CORE))

    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.APP_COMPAT_RESOURCES)
    implementation(Deps.Android.Core.FRAGMENT)
    implementation(Deps.Log.TIMBER)

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                Projects.Implementation.Android.Core.CORE, // Ignore remove advise. Required for theme purposes.
                Deps.Android.Core.APP_COMPAT.identifier(), // Ignore remove advise. Else, 'AlertDialog' is unresolved.
            )
        }
        onIncorrectConfiguration {
            exclude(
                Deps.Android.Core.FRAGMENT.identifier(), // Ignore change to 'api' advice.
            )
        }
    }
}
