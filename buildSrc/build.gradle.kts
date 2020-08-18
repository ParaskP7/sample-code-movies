@file:Suppress("InvalidPackageDeclaration")

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

gradlePlugin {
    plugins {
        register("io.petros.movies.coverage") {
            id = "io.petros.movies.coverage"
            implementationClass = "io.petros.movies.plugin.coverage.CoveragePlugin"
        }
    }
}

/* CONFIGURATION */

object Repos {

    object Url {

        object Kotlin {

            const val KOTLIN_EAP = "https://dl.bintray.com/kotlin/kotlin-eap"

        }

    }

}

repositories {
    jcenter()
    maven(Repos.Url.Kotlin.KOTLIN_EAP)
}
