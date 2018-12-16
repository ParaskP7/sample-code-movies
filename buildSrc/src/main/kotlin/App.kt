object App {

    object Version {

        private const val MAJOR = 1
        private const val MINOR = 0
        private const val PATCH = 0
        private const val BUILD = 0

        const val CODE = Version.MAJOR * 10000 + Version.MINOR * 1000 + Version.PATCH * 100 + Version.BUILD
        const val NAME = "${Version.MAJOR}.${Version.MINOR}.${Version.PATCH}"

    }

    object Debug {

        object Suffix {

            private const val SUFFIX = "debug"

            const val APPLICATION_ID = ".$SUFFIX"
            const val VERSION_NAME = "-$SUFFIX"

        }

    }

    const val APPLICATION_ID = "io.petros.movies"

}
