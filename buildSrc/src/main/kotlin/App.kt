object App {

    object Version {

        const val MAJOR = 1
        const val MINOR = 0
        const val PATCH = 0
        const val BUILD = 0

    }

    /* DEFAULT CONFIG */

    const val APPLICATION_ID = "io.petros.movies"
    const val VERSION_CODE = Version.MAJOR * 10000 + Version.MINOR * 1000 + Version.PATCH * 100 + Version.BUILD
    const val VERSION_NAME = "${Version.MAJOR}.${Version.MINOR}.${Version.PATCH}"

    /* BUILD TYPES - DEBUG */

    private const val DEBUG_SUFFIX = "debug"

    const val DEBUG_APPLICATION_ID_SUFFIX = ".$DEBUG_SUFFIX"
    const val DEBUG_VERSION_NAME_SUFFIX = "-$DEBUG_SUFFIX"

}
