package io.petros.movies

object Projects {

    object Implementation {

        object Kotlin {

            const val UTILS = "${Utils.COLON}utils"
            const val DOMAIN = "${Utils.COLON}domain"
            const val NETWORK = "${Utils.COLON}network"

        }

        object Android {

            @Suppress("MemberNameEqualsClassName", "MemberVisibilityCanBePrivate")
            object Core {

                const val ANDROID_UTILS = "${Utils.COLON}android-utils"
                const val DATASTORE = "${Utils.COLON}datastore"
                const val DATABASE = "${Utils.COLON}database"
                const val DATA = "${Utils.COLON}data"
                const val CORE = "${Utils.COLON}core"
                const val CORE_COMPOSE = "$CORE-compose"

            }

            object Lib {

                const val PICKER = "${Utils.COLON}lib${Utils.COLON}picker"

            }

            @Suppress("MemberVisibilityCanBePrivate")
            object Feature {

                const val MOVIES = "${Utils.COLON}feature${Utils.COLON}movies"
                const val MOVIE_DETAILS = "${Utils.COLON}feature${Utils.COLON}movie-details"
                const val MOVIE_DETAILS_COMPOSE = "$MOVIE_DETAILS-compose"

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
