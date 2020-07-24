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

object Plugins {

    object Version {

        // Releases: https://blog.jetbrains.com/kotlin/category/releases
        @Suppress("unused") const val KOTLIN = "1.3.72" // Released: 14-04-20
        const val KOTLIN_EAP = "1.4-M3" // Released: 06-07-20

    }

}

object Deps {

    object Kotlin {

        object Core {

            const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${Plugins.Version.KOTLIN_EAP}"

        }

    }

}

repositories {
    jcenter()
    maven(Repos.Url.Kotlin.KOTLIN_EAP)
}

dependencies {
    implementation(Deps.Kotlin.Core.KOTLIN)
}
