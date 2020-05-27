package io.petros.movies.config.android

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

object LocalProperties {

    const val FILE = "local.properties"

    object Gradle {

        const val IGNORED_VARIANTS = "gradle.ignored.variants"

    }

    object TheMoviesDb {

        const val API_KEY = "themoviedb.api.key"

    }

}

fun Project.findLocalProperty(property: String): String? {
    val file = file("${project.rootDir}/${LocalProperties.FILE}")
    return if (file.exists()) {
        val localProperties = Properties()
        localProperties.load(FileInputStream(file))
        localProperties[property]?.toString()
    } else {
        println("The '${LocalProperties.FILE}' file does not exist.")
        null
    }
}
