@file:Suppress("InvalidPackageDeclaration")

import io.petros.movies.config.deps.Deps
import io.petros.movies.config.deps.identifier

plugins {
    id(Plugins.Id.Android.LIBRARY)
    id(Plugins.Id.Kotlin.Android.ANDROID)
    id(Plugins.Id.Quality.DETEKT)
    id(Plugins.Id.Dependency.VERSIONS)
}

dependencies {
    implementation(project(Deps.Project.Implementation.Android.Core.CORE))

    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.FRAGMENT)
    api(Deps.Util.MONTH_YEAR_PICKER) // Usage of api is required because of 'MovieActivity', which cannot access it.

    detektPlugins(Plugins.DETEKT_FORMATTING)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(
                Deps.Project.Implementation.Android.Core.CORE, // Ignore remove advise. Required for theme purposes.
                Deps.Android.Core.APP_COMPAT.identifier() // Ignore remove advise. Otherwise, 'AlertDialog' is unresolved.
            )
        }
        onIncorrectConfiguration {
            exclude(
                Deps.Android.Core.FRAGMENT.identifier() // Ignore change to 'api' advice.
            )
        }
    }
}
