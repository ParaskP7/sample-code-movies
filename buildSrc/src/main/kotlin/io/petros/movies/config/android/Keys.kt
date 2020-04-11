package io.petros.movies.config.android

import io.petros.movies.config.dirs.Files
import io.petros.movies.config.dirs.Folders

object Keys {

    private const val KEYS_DIRECTORY = "${Folders.PARENT}/${Folders.Config.CONFIG}/${Folders.Config.Subfolder.KEYS}"

    object TheMoviesDb {

        object Config {

            const val API_FILE_PATH = "$KEYS_DIRECTORY/${Files.Properties.THEMOVIEDB_API}"
            const val API_KEY_CONST = "THEMOVIEDB_API_KEY"

        }

        object Property {

            const val API_KEY = "themoviedb_api_key"

        }

    }

}
