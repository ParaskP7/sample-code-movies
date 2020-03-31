@file:Suppress("InvalidPackageDeclaration")

object Project {

    object Implementation {

        object Kotlin {

            const val UTILS = "${Utils.COLON}utils"
            const val DOMAIN = "${Utils.COLON}domain"
            const val NETWORK = "${Utils.COLON}network"

        }

        object Android {

            @Suppress("MemberNameEqualsClassName")
            object Core {

                const val ANDROID_UTILS = "${Utils.COLON}android-utils"
                const val DATA = "${Utils.COLON}data"
                const val CORE = "${Utils.COLON}core"

            }

            object Feature {

                const val SPLASH = "${Utils.COLON}feature${Utils.COLON}splash"
                const val MOVIES = "${Utils.COLON}feature${Utils.COLON}movies"
                const val PICKER = "${Utils.COLON}feature${Utils.COLON}picker"
                const val MOVIE_DETAILS = "${Utils.COLON}feature${Utils.COLON}movie-details"

            }

        }

    }

    object TestImplementation {

        object Kotlin {

            const val TEST = "${Utils.COLON}test"

        }

        object Android {

            const val ANDROID_TEST = "${Utils.COLON}android-test"

        }

    }

}
