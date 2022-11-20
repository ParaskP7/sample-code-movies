package io.petros.movies

import com.google.devtools.ksp.gradle.KspExtension

internal fun configureKsp(
    kspExtension: KspExtension,
) {
    kspExtension.apply {
        allWarningsAsErrors = true
    }
}
