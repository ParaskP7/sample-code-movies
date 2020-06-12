package io.petros.movies.config.deps

import io.petros.movies.config.utils.Utils

object Projects {

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

            object Lib {

                const val PICKER = "${Utils.COLON}lib${Utils.COLON}picker"

            }

            object Feature {

                const val MOVIES = "${Utils.COLON}feature${Utils.COLON}movies"
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
