object Config {

    /* GRADLE */
    const val GRADLE_ANDROID =
        "${Folders.PARENT}/${Folders.CONFIG}/${Folders.GRADLE}/${Folders.ANDROID}/${Files.GRADLE_ANDROID}"
    const val GRADLE_DART =
        "${Folders.PARENT}/${Folders.CONFIG}/${Folders.GRADLE}/${Folders.ANDROID}/${Files.GRADLE_DART}"
    const val GRADLE_DEXCOUNT =
        "${Folders.PARENT}/${Folders.CONFIG}/${Folders.GRADLE}/${Folders.ANDROID}/${Files.GRADLE_DEXCOUNT}"
    const val GRADLE_LEAK_CANARY =
        "${Folders.PARENT}/${Folders.CONFIG}/${Folders.GRADLE}/${Folders.ANDROID}/${Files.GRADLE_LEAK_CANARY}"
    const val GRADLE_DEPENDENCY_UPDATES =
        "${Folders.PARENT}/${Folders.CONFIG}/${Folders.GRADLE}/${Folders.DEPENDENCIES}/${Files.GRADLE_DEPENDENCY_UPDATES}"
    const val GRADLE_DETEKT =
        "${Folders.PARENT}/${Folders.CONFIG}/${Folders.GRADLE}/${Folders.QUALITY}/${Files.GRADLE_DETEKT}"
    const val GRADLE_LINT =
        "${Folders.PARENT}/${Folders.CONFIG}/${Folders.GRADLE}/${Folders.QUALITY}/${Files.GRADLE_LINT}"

    /* KEYS */

    const val KEYS_THEMOVIEDB_API_FILE_PATH =
        "${Folders.PARENT}/${Folders.CONFIG}/${Folders.KEYS}/${Files.PROPERTIES_THEMOVIEDB_API}"

    /* DEXCOUNT */

    const val DEXCOUNT_FORMAT = "list"

    /* DETEKT */

    const val DETEKT_CONFIG_FILE_PATH = "${Folders.PARENT}/${Folders.CONFIG}/${Folders.QUALITY}/${Files.YML_DETEKT}"
    const val DETEKT_FILTERS = ".*/${Folders.TEST}/.*" + Utils.COMMA +
            ".*/${Folders.ROBOLECTRIC_TEST}/.*" + Utils.COMMA +
            ".*/${Folders.ANDROID_TEST}/.*" + Utils.COMMA +
            ".*/${Folders.RESOURCES}/.*"

    /* LINT */

    const val LINT_CONFIG_FILE_PATH = "${Folders.PARENT}/${Folders.CONFIG}/${Folders.QUALITY}/${Files.XML_LINT}"
    val lintListOfDisabledIssues = arrayOf(
        "MissingRegistered", // Because of UAST
        "UnusedIds", // Because of UAST
        "ContentDescription",
        "SelectableText"
    )

}
