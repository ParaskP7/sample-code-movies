object Config {

    /* KEYS */

    const val KEYS_THEMOVIEDB_API_FILE_PATH = "${Folders.PARENT}/${Folders.CONFIG}/${Folders.KEYS}/${Files.THEMOVIEDB_API}"

    /* DEXCOUNT */

    const val DEXCOUNT_FORMAT = "list"

    /* DETEKT */

    const val DETEKT_CONFIG_FILE_PATH = "${Folders.PARENT}/${Folders.CONFIG}/${Folders.QUALITY}/${Files.DETEKT}"
    const val DETEKT_FILTERS = ".*/${Folders.TEST}/.*" + Utils.COMMA +
            ".*/${Folders.ROBOLECTRIC_TEST}/.*" + Utils.COMMA +
            ".*/${Folders.ANDROID_TEST}/.*" + Utils.COMMA +
            ".*/${Folders.RESOURCES}/.*"

    /* LINT */

    const val LINT_CONFIG_FILE_PATH = "${Folders.PARENT}/${Folders.CONFIG}/${Folders.QUALITY}/${Files.LINT}"
    val lintListOfDisabledIssues = arrayOf(
        "MissingRegistered", // Because of UAST
        "UnusedIds", // Because of UAST
        "ContentDescription",
        "SelectableText"
    )

}
