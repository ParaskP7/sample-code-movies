object Config {

    private const val KEYS_DIRECTORY = "${Folders.PARENT}/${Folders.Config.CONFIG}/${Folders.Config.Subfolder.KEYS}"
    private const val QUALITY_DIRECTORY = "${Folders.Config.CONFIG}/${Folders.Config.Subfolder.QUALITY}"

    object Lint {

        const val CONFIG_FILE_PATH = "${QUALITY_DIRECTORY}/${Files.Xml.LINT}"

        val disabledIssues = arrayOf(
            "MissingRegistered", // Because of UAST
            "UnusedIds", // Because of UAST
            "ContentDescription",
            "SelectableText"
        )

    }

    // TODO: Re-add Detekt plugin.

    object Keys {

        object TheMoviesDb {

            const val API_FILE_PATH = "${KEYS_DIRECTORY}/${Files.Properties.THEMOVIEDB_API}"
            const val API_KEY_CONST = "THEMOVIEDB_API_KEY"

        }

    }

}
