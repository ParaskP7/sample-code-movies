@file:Suppress("InvalidPackageDeclaration")

object Config {

    private const val KEYS_DIRECTORY = "${Folders.PARENT}/${Folders.Config.CONFIG}/${Folders.Config.Subfolder.KEYS}"
    private const val QUALITY_DIRECTORY = "${Folders.Config.CONFIG}/${Folders.Config.Subfolder.QUALITY}"

    object Lint {

        const val CONFIG_FILE_PATH = "$QUALITY_DIRECTORY/${Files.Xml.LINT}"

        val disabledIssues = arrayOf(
            "MissingRegistered", // Because of UAST
            "UnusedIds", // Because of UAST
            "ContentDescription",
            "SelectableText"
        )

    }

    object Detekt {

        const val CONFIG_FILE_PATH = "$QUALITY_DIRECTORY/${Files.Yml.DETEKT}"

    }

    object Versions {

        const val GRADLE_RELEASE_CHANNEL = "release-candidate"
        const val OUTPUT_FORMATTER = "json,xml,txt"
        const val OUTPUT_DIR = "build/dependencyUpdates"
        const val REPORT_FILE_NAME = "report"

        const val REGEX = "^[0-9,.v-]+(-r)?$"
        val stableKeyword = listOf("RELEASE", "FINAL", "GA")
        val nonStableKeyword = listOf("M1")

    }

    object Keys {

        object TheMoviesDb {

            const val API_FILE_PATH = "$KEYS_DIRECTORY/${Files.Properties.THEMOVIEDB_API}"
            const val API_KEY_CONST = "THEMOVIEDB_API_KEY"

        }

    }

}
