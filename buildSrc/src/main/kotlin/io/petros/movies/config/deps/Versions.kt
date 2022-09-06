package io.petros.movies.config.deps

@Suppress("StringLiteralDuplication")
object Versions {

    // DEBUG IMPLEMENTATION // *****************************************************************************************

    object LeakCanary {

        // Releases: https://github.com/square/leakcanary/releases
        const val LEAK_CANARY = "2.9.1" // Released: 20-04-22

    }

    // IMPLEMENTATION // ***********************************************************************************************

    object Kotlin {

        // Releases: https://github.com/Kotlin/kotlinx.coroutines/releases
        const val COROUTINES = "1.6.4" // Released: 13-07-22

    }

    @Suppress("MemberNameEqualsClassName")
    object Material {

        // Releases: https://github.com/material-components/material-components-android/releases
        @Suppress("unused") const val MATERIAL = "1.6.1" // Released: 31-05-22
        const val MATERIAL_RC = "1.7.0-rc01" // Released: 30-08-22

    }

    object Android {

        object Core {

            // Releases: https://developer.android.com/jetpack/androidx/releases/appcompat
            const val APP_COMPAT = "1.4.2" // Released: 01-06-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/fragment
            @Suppress("unused") const val FRAGMENT = "1.4.1" // Released: 26-01-22
            const val FRAGMENT_RC = "1.5.0-rc01" // Released: 11-05-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/recyclerview
            const val RECYCLER_VIEW = "1.2.1" // Released: 02-06-21

            // Releases: https://developer.android.com/jetpack/androidx/releases/constraintlayout
            const val CONSTRAINT_LAYOUT = "2.1.4" // Released: 20-05-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/drawerlayout
            const val DRAWER_LAYOUT = "1.1.1" // Released: 02-09-20

            // Releases: https://developer.android.com/jetpack/androidx/releases/coordinatorlayout
            const val COORDINATOR_LAYOUT = "1.2.0" // Released: 12-01-22

        }

        object Ktx {

            // Releases: https://developer.android.com/jetpack/androidx/releases/core
            const val CORE = "1.8.0" // Released: 01-06-22

        }

        @Suppress("MemberNameEqualsClassName")
        object Compose {

            // Releases: https://developer.android.com/jetpack/androidx/releases/compose
            const val COMPOSE = "1.3.0" // Released: 10-08-22
            const val COMPOSE_BETA = "1.3.0-beta01" // Released: 24-08-22

            object ConstraintLayout {

                // Releases: https://developer.android.com/jetpack/androidx/releases/constraintlayout
                const val CONSTRAINT_LAYOUT = "1.0.1" // Released: 20-05-22

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
                @Suppress("unused") const val LIFECYCLE = "2.4.1" // Released: 09-02-22
                const val LIFECYCLE_RC = "2.5.0-rc01" // Released: 11-05-22

            }

            @Suppress("MemberNameEqualsClassName")
            object SavedState {

                // Releases: https://developer.android.com/jetpack/androidx/releases/savedstate
                @Suppress("unused") const val SAVED_STATE = "1.1.0" // Released: 10-02-20
                const val SAVED_STATE_RC = "1.2.0-rc01" // Released: 11-05-20

            }

            @Suppress("MemberNameEqualsClassName")
            object Navigation {

                // Releases: https://developer.android.com/jetpack/androidx/releases/navigation
                @Suppress("unused") const val NAVIGATION = "2.4.1" // Released: 23-02-22
                const val NAVIGATION_RC = "2.5.0-rc01" // Released: 11-05-22

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
                const val PAGING = "3.1.1" // Release: 09-03-22

            }

        }

        object Test {

            // Releases: https://developer.android.com/jetpack/androidx/releases/test
            const val CORE = "1.4.0" // Released: 30-06-21
            const val ESPRESSO = "3.4.0" // Released: 30-06-21
            const val J_UNIT = "1.1.3" // Released: 30-06-21

            // Releases: https://github.com/robolectric/robolectric/releases
            const val ROBOLECTRIC = "4.8.1" // Released: 03-05-22

        }

    }

    object Di {

        @Suppress("MemberNameEqualsClassName")
        object Koin {

            // Releases: https://github.com/InsertKoinIO/koin/releases
            const val KOIN = "3.2.0" // Released: 11-05-22

        }

    }

    object Net {

        object OkHttp {

            // Releases: https://github.com/square/okhttp/releases
            @Suppress("unused") const val OK_HTTP = "4.10.0" // Released: 12-06-22
            const val OK_HTTP_ALPHA = "5.0.0-alpha.8" // Released: 08-06-22

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
            const val GLIDE = "4.13.2" // Released: 04-05-22

        }

        @Suppress("MemberNameEqualsClassName")
        object Coil {

            // Releases: https://github.com/coil-kt/coil/releases
            const val COIL = "2.1.0" // Released: 17-05-22

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
            const val MOCK_K = "1.12.4" // Released: 11-05-22

        }

    }

}
