object Config {

    private const val KEYS_DIRECTORY = "${Folders.PARENT}/${Folders.Config.CONFIG}/${Folders.Config.KEYS}"
    private const val QUALITY_DIRECTORY = "${Folders.Config.CONFIG}/${Folders.Config.QUALITY}"

    object Gradle {

        private const val GRADLE_DIRECTORY = "${Folders.PARENT}/${Folders.Config.CONFIG}/${Folders.Config.GRADLE}"

        const val ANDROID = "${GRADLE_DIRECTORY}/${Folders.Config.ANDROID}/${Files.Gradle.ANDROID}"
        const val DEPENDENCY_UPDATES = "${GRADLE_DIRECTORY}/${Folders.Config.DEPENDENCY}/${Files.Gradle.DEPENDENCY_UPDATES}"

    }

    object Dart {

        object Kapt {

            const val NAME = "dart.henson.package"

        }

    }

    object Dexcount {

        const val FORMAT = "list"
        const val MAX_METHOD_COUNT = 60000

    }

    object Lint {

        const val CONFIG_FILE_PATH = "${QUALITY_DIRECTORY}/${Files.Xml.LINT}"

        val disabledIssues = arrayOf(
            "MissingRegistered", // Because of UAST
            "UnusedIds", // Because of UAST
            "ContentDescription",
            "SelectableText"
        )

    }

    object Detekt {

        const val CONFIG_FILE_PATH = "${Folders.PARENT}/${QUALITY_DIRECTORY}/${Files.Yml.DETEKT}"
        const val FILTERS = ".*/${Folders.Source.TEST}/.*" + Utils.COMMA +
                ".*/${Folders.Source.ROBOLECTRIC_TEST}/.*" + Utils.COMMA +
                ".*/${Folders.Source.ANDROID_TEST}/.*" + Utils.COMMA +
                ".*/${Folders.Source.RESOURCES}/.*"

    }

    object Keys {

        object TheMoviesDb {

            const val API_FILE_PATH = "${KEYS_DIRECTORY}/${Files.Properties.THEMOVIEDB_API}"
            const val API_KEY_CONST = "THEMOVIEDB_API_KEY"

        }

    }

}
