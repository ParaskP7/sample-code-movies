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
    sourceCompatibility = Java.version
    targetCompatibility = Java.version
}

/* CONFIG */

object Java {

    val version = JavaVersion.VERSION_11

}
