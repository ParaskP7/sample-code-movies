plugins {
    `kotlin-dsl`
}

group = "io.petros.movies.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.detekt.gradle.plugin)
    compileOnly(libs.dependency.analysis.gradle.plugin)
    compileOnly(libs.dependency.versions.gradle.plugin)
    compileOnly(libs.gradle.doctor.gradle.plugin)
    compileOnly(libs.modules.graph.assert.gradle.plugin)
}

gradlePlugin {
    plugins {
        /* KOTLIN PLUGINS */

        register("kotlin") {
            id = "custom.kotlin"
            implementationClass = "KotlinConventionPlugin"
        }

        /* ANDROID PLUGINS - APPLICATION */

        register("androidApplication") {
            id = "custom.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "custom.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        /* ANDROID PLUGINS - LIBRARY */

        register("androidLibrary") {
            id = "custom.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "custom.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        /* QUALITY PLUGINS */

        register("detekt") {
            id = "custom.detekt"
            implementationClass = "DetektConventionPlugin"
        }

        /* TEST PLUGINS */

        register("jacoco") {
            id = "custom.jacoco"
            implementationClass = "JacocoConventionPlugin"
        }

        /* DEPENDENCY PLUGINS */

        register("dependencyVersions") {
            id = "custom.dependency.versions"
            implementationClass = "DependencyVersionsConventionPlugin"
        }
        register("dependencyAnalysis") {
            id = "custom.dependency.analysis"
            implementationClass = "DependencyAnalysisConventionPlugin"
        }

        /* GRADLE */

        register("gradleDoctor") {
            id = "custom.gradle.doctor"
            implementationClass = "GradleDoctorConventionPlugin"
        }

        /* MODULE PLUGINS */

        register("modulesGraphAssert") {
            id = "custom.modules.graph.assert"
            implementationClass = "ModulesGraphAssertConventionPlugin"
        }
    }
}
