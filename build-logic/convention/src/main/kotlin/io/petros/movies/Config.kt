package io.petros.movies

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

fun Project.findLocalProperty(property: String): String? {
    val file = file("${project.rootDir}/${Config.LocalProperties.FILE}")
    return if (file.exists()) {
        val localProperties = Properties()
        localProperties.load(FileInputStream(file))
        localProperties[property]?.toString()
    } else {
        println("The '${Config.LocalProperties.FILE}' file does not exist.")
        null
    }
}

object Config {

    const val QUALITY_DIRECTORY = "${Folders.CONFIG}/${Folders.Subfolder.QUALITY}"

    object Folders {

        const val CONFIG = "config"

        object Subfolder {

            const val QUALITY = "quality"

        }

    }

    object Gradle {

        const val THEMOVIEDB_API_KEY_CONST = "THEMOVIEDB_API_KEY"

    }

    object BuildConfig {

        object Field {

            const val STRING = "String"

        }

    }

    object LocalProperties {

        const val FILE = "local.properties"

        object Gradle {

            const val THEMOVIEDB_API_KEY_CONST = "THEMOVIEDB_API_KEY"

            const val IGNORED_VARIANTS = "gradle.ignored.variants"

        }

        object TheMoviesDb {

            const val API_KEY = "themoviedb.api.key"

        }

    }

}
