buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.dependency.versions) apply false
    alias(libs.plugins.dependency.analysis) apply false
    id("custom.dependency.analysis") // You must apply the plugin to the root project.
    alias(libs.plugins.gradle.doctor) apply false
    id("custom.gradle.doctor") // You must apply the plugin to the root project.
    alias(libs.plugins.modules.graph.assert) apply false
}
