package io.petros.movies

import org.gradle.api.JavaVersion
import org.gradle.api.plugins.JavaPluginExtension

internal fun configureJava(
    javaPluginExtension: JavaPluginExtension,
) {
    javaPluginExtension.apply {
        compatibility()
    }
}

/* COMPATIBILITY */

fun JavaPluginExtension.compatibility() {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
