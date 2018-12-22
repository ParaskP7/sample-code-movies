object Config {

    private const val KEYS_DIRECTORY = "${Folders.PARENT}/${Folders.Config.CONFIG}/${Folders.Config.KEYS}"
    private const val QUALITY_DIRECTORY = "${Folders.PARENT}/${Folders.Config.CONFIG}/${Folders.Config.QUALITY}"

    object Gradle {

        private const val GRADLE_DIRECTORY = "${Folders.PARENT}/${Folders.Config.CONFIG}/${Folders.Config.GRADLE}"

        const val ANDROID = "${GRADLE_DIRECTORY}/${Folders.Config.ANDROID}/${Files.Gradle.ANDROID}"
        const val DART = "${GRADLE_DIRECTORY}/${Folders.Config.ANDROID}/${Files.Gradle.DART}"
        const val DEXCOUNT = "${GRADLE_DIRECTORY}/${Folders.Config.ANDROID}/${Files.Gradle.DEXCOUNT}"
        const val LEAK_CANARY = "${GRADLE_DIRECTORY}/${Folders.Config.ANDROID}/${Files.Gradle.LEAK_CANARY}"
        const val DEPENDENCY_UPDATES = "${GRADLE_DIRECTORY}/${Folders.Config.DEPENDENCY}/${Files.Gradle.DEPENDENCY_UPDATES}"
        const val DETEKT = "${GRADLE_DIRECTORY}/${Folders.Config.QUALITY}/${Files.Gradle.DETEKT}"
        const val LINT = "${GRADLE_DIRECTORY}/${Folders.Config.QUALITY}/${Files.Gradle.LINT}"

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

    object Detekt {

        const val CONFIG_FILE_PATH = "${QUALITY_DIRECTORY}/${Files.Yml.DETEKT}"
        const val FILTERS = ".*/${Folders.Source.TEST}/.*" + Utils.COMMA +
                ".*/${Folders.Source.ROBOLECTRIC_TEST}/.*" + Utils.COMMA +
                ".*/${Folders.Source.ANDROID_TEST}/.*" + Utils.COMMA +
                ".*/${Folders.Source.RESOURCES}/.*"

    }

    /* KEYS */

    const val KEYS_THEMOVIEDB_API_FILE_PATH = "${KEYS_DIRECTORY}/${Files.Properties.THEMOVIEDB_API}"

    /* LINT */

    const val LINT_CONFIG_FILE_PATH = "${QUALITY_DIRECTORY}/${Files.Xml.LINT}"
    val lintDisabledIssues = arrayOf(
        "MissingRegistered", // Because of UAST
        "UnusedIds", // Because of UAST
        "ContentDescription",
        "SelectableText"
    )

}
