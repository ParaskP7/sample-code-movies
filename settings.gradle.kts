@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "movies"

/* UTIL MODULES */

include(":utils")
include(":android-utils")

/* TEST MODULES */

include(":test")
include(":android-test")

/* DOMAIN MODULES */

include(":domain")

/* DATA MODULES */

include(":network")
include(":datastore")
include(":database")
include(":data")

/* APP MODULE */

include(":app")

/* CORE MODULES */

include(":core")
include(":core-compose")

/* LIB MODULES */

include(":lib:picker")

/* FEATURE MODULES */

include(":feature:movies")
include(":feature:movie-details")
include(":feature:movie-details-compose")
