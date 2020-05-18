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

repositories {
    jcenter()
}
