package io.petros.movies.config.deps

@Suppress("StringLiteralDuplication")
object Versions {

    // DEBUG IMPLEMENTATION // *****************************************************************************************

    object LeakCanary {

        // Releases: https://github.com/square/leakcanary/releases
        const val LEAK_CANARY = "2.8.1" // Released: 06-01-22

    }

    // IMPLEMENTATION // ***********************************************************************************************

    object Kotlin {

        // Releases: https://github.com/Kotlin/kotlinx.coroutines/releases
        const val COROUTINES = "1.6.0" // Released: 22-12-21

    }

    @Suppress("MemberNameEqualsClassName")
    object Material {

        // Releases: https://github.com/material-components/material-components-android/releases
        const val MATERIAL = "1.5.0" // Released: 13-01-22

    }

    object Android {

        object Core {

            // Releases: https://developer.android.com/jetpack/androidx/releases/appcompat
            const val APP_COMPAT = "1.4.1" // Released: 12-01-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/fragment
            const val FRAGMENT = "1.4.1" // Released: 26-01-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/recyclerview
            const val RECYCLER_VIEW = "1.2.1" // Released: 02-06-21

            // Releases: https://developer.android.com/jetpack/androidx/releases/constraintlayout
            const val CONSTRAINT_LAYOUT = "2.1.3" // Released: 14-01-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/drawerlayout
            const val DRAWER_LAYOUT = "1.1.1" // Released: 02-09-20

            // Releases: https://developer.android.com/jetpack/androidx/releases/coordinatorlayout
            const val COORDINATOR_LAYOUT = "1.2.0" // Released: 12-01-22

        }

        object Ktx {

            // Releases: https://developer.android.com/jetpack/androidx/releases/core
            const val CORE = "1.7.0" // Released: 27-10-21

        }

        @Suppress("MemberNameEqualsClassName")
        object Compose {

            // Releases: https://developer.android.com/jetpack/androidx/releases/compose
            const val COMPOSE = "1.1.1" // Released: 23-02-22

            object ConstraintLayout {

                // Releases: https://developer.android.com/jetpack/androidx/releases/constraintlayout
                const val CONSTRAINT_LAYOUT = "1.0.0" // Released: 14-01-22

            }

        }

        object Arch {

            @Suppress("MemberNameEqualsClassName")
            object Core {

                // Releases: https://developer.android.com/jetpack/androidx/releases/arch-core
                const val CORE = "2.1.0" // Released: 05-09-19

            }

            @Suppress("MemberNameEqualsClassName")
            object Lifecycle {

                // Releases: https://developer.android.com/jetpack/androidx/releases/lifecycle
                const val LIFECYCLE = "2.4.1" // Released: 09-02-22

            }

            @Suppress("MemberNameEqualsClassName")
            object SavedState {

                // Releases: https://developer.android.com/jetpack/androidx/releases/savedstate
                const val SAVED_STATE = "1.1.0" // Released: 10-02-20

            }

            @Suppress("MemberNameEqualsClassName")
            object Navigation {

                // Releases: https://developer.android.com/jetpack/androidx/releases/navigation
                const val NAVIGATION = "2.4.1" // Released: 23-02-22

            }

            object CustomView {

                // Releases: https://developer.android.com/jetpack/androidx/releases/customview
                const val CUSTOM_VIEW = "1.1.0" // Release: 24-06-20

            }

            @Suppress("MemberNameEqualsClassName")
            object DataStore {

                // Releases: https://developer.android.com/jetpack/androidx/releases/datastore
                const val DATASTORE = "1.0.0" // Release: 04-08-21

            }

            object Database {

                @Suppress("MemberNameEqualsClassName")
                object SQLight {

                    // Releases: https://developer.android.com/jetpack/androidx/releases/sqlite
                    const val SQLIGHT = "2.2.0" // Release: 15-12-21

                }

                @Suppress("MemberNameEqualsClassName")
                object Room {

                    // Releases: https://developer.android.com/jetpack/androidx/releases/room
                    const val ROOM = "2.4.2" // Release: 23-02-22

                }

            }

            @Suppress("MemberNameEqualsClassName")
            object Paging {

                // Releases: https://developer.android.com/jetpack/androidx/releases/paging
                const val PAGING = "3.1.0" // Release: 17-11-21

            }

        }

        object Test {

            // Releases: https://developer.android.com/jetpack/androidx/releases/test
            const val CORE = "1.4.0" // Released: 30-06-21
            const val ESPRESSO = "3.4.0" // Released: 30-06-21
            const val J_UNIT = "1.1.3" // Released: 30-06-21

            // Releases: https://github.com/robolectric/robolectric/releases
            const val ROBOLECTRIC = "4.7.3" // Released: 01-12-21

        }

    }

    object Di {

        @Suppress("MemberNameEqualsClassName")
        object Koin {

            // Releases: https://github.com/InsertKoinIO/koin/releases
            @Suppress("unused") const val KOIN = "3.1.5" // Released: 17-12-21
            const val KOIN_BETA = "3.2.0-beta-1" // Released: 15-02-22

        }

    }

    object Net {

        object OkHttp {

            // Releases: https://github.com/square/okhttp/releases
            @Suppress("unused") const val OK_HTTP = "4.9.3" // Released: 21-11-21
            @Suppress("unused") const val OK_HTTP_RC = "4.10.0-RC1" // Released: 07-10-21
            const val OK_HTTP_ALPHA = "5.0.0-alpha.5" // Released: 21-02-22

        }

        @Suppress("MemberNameEqualsClassName")
        object Gson {

            // Releases: https://github.com/google/gson/releases
            const val GSON = "2.9.0" // Released: 11-02-22

        }

        object Rest {

            // Releases: https://github.com/square/retrofit/releases
            const val RETROFIT = "2.9.0" // Released: 20-05-20

        }

    }

    object Image {

        @Suppress("MemberNameEqualsClassName")
        object Glide {

            // Releases: https://github.com/bumptech/glide/releases
            const val GLIDE = "4.13.1" // Released: 23-02-22

        }

        @Suppress("MemberNameEqualsClassName")
        object Coil {

            // Releases: https://github.com/coil-kt/coil/releases
            @Suppress("unused") const val COIL = "1.4.0" // Released: 06-10-21
            const val COIL_RC = "2.0.0-rc01" // Released: 02-03-22

        }

    }

    object Log {

        // Releases: https://github.com/JakeWharton/timber/releases
        const val TIMBER = "5.0.1" // Released: 13-08-18

    }

    // TEST IMPLEMENTATION // ******************************************************************************************

    object Test {

        object JUnit {

            // Releases: https://github.com/junit-team/junit4/releases
            const val J_UNIT_4 = "4.13.2" // Released: 13-02-21

        }

        @Suppress("MemberNameEqualsClassName")
        object Hamcrest {

            // Releases: https://github.com/hamcrest/JavaHamcrest/releases
            const val HAMCREST = "1.3" // Released: 10-06-12

        }

        object Assert {

            // Releases: https://github.com/robfletcher/strikt/releases
            const val STRIKT = "0.34.1" // Released: 03-02-22

        }

        object Mock {

            // Releases: https://github.com/mockk/mockk/releases
            const val MOCK_K = "1.12.3" // Released: 28-02-22

        }

    }

}
