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
            @Suppress("unused") const val APP_COMPAT = "1.5.0" // Released: 10-08-22
            const val APP_COMPAT_RC = "1.6.0-rc01" // Released: 07-09-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/annotation
            const val ANNOTATION = "1.5.0" // Released: 21-09-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/fragment
            const val FRAGMENT = "1.5.3" // Released: 21-09-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/recyclerview
            @Suppress("unused") const val RECYCLER_VIEW = "1.2.1" // Released: 02-06-21
            const val RECYCLER_VIEW_RC = "1.3.0-rc01" // Released: 21-09-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/constraintlayout
            const val CONSTRAINT_LAYOUT = "2.1.4" // Released: 20-05-22

            // Releases: https://developer.android.com/jetpack/androidx/releases/drawerlayout
            const val DRAWER_LAYOUT = "1.1.1" // Released: 02-09-20

            // Releases: https://developer.android.com/jetpack/androidx/releases/coordinatorlayout
            const val COORDINATOR_LAYOUT = "1.2.0" // Released: 12-01-22

        }

        object Ktx {

            // Releases: https://developer.android.com/jetpack/androidx/releases/core
            const val CORE = "1.9.0" // Released: 07-09-22

        }

        @Suppress("MemberNameEqualsClassName")
        object Compose {

            object Compiler {

                // Releases: https://developer.android.com/jetpack/androidx/releases/compose-compiler
                const val COMPILER = "1.3.2" // Released: 04-10-22

            }

            object Libraries {

                // Releases: https://developer.android.com/jetpack/androidx/releases/compose
                @Suppress("unused") const val LIBRARIES = "1.2.1" // Released: 10-08-22
                const val LIBRARIES_RC = "1.3.0-rc01" // Released: 05-10-22

            }

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
                const val LIFECYCLE = "2.5.1" // Released: 27-07-22

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
                const val NAVIGATION = "2.5.2" // Released: 07-09-22

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
                    @Suppress("unused") const val SQLIGHT = "2.2.0" // Release: 15-12-21
                    const val SQLIGHT_BETA = "2.3.0-beta01" // Release: 05-10-22

                }

                @Suppress("MemberNameEqualsClassName")
                object Room {

                    // Releases: https://developer.android.com/jetpack/androidx/releases/room
                    @Suppress("unused") const val ROOM = "2.4.3" // Release: 24-08-22
                    const val ROOM_BETA = "2.5.0-beta01" // Release: 05-10-22

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
            @Suppress("unused") const val CORE = "1.4.0" // Released: 30-06-21
            const val CORE_BETA = "1.5.0-beta01" // Released: ??-10-22
            @Suppress("unused") const val ESPRESSO = "3.4.0" // Released: 30-06-21
            const val ESPRESSO_BETA = "3.5.0-beta01" // Released: ??-10-22
            @Suppress("unused") const val J_UNIT = "1.1.3" // Released: 30-06-21
            const val J_UNIT_BETA = "1.1.4-beta01" // Released: ??-10-22

            // Releases: https://github.com/robolectric/robolectric/releases
            const val ROBOLECTRIC = "4.9" // Released: 30-09-22

        }

    }

    object Di {

        @Suppress("MemberNameEqualsClassName")
        object Koin {

            // Releases: https://github.com/InsertKoinIO/koin/releases
            const val KOIN = "3.2.2" // Released: 23-09-22

        }

    }

    object Net {

        object OkHttp {

            // Releases: https://github.com/square/okhttp/releases
            @Suppress("unused") const val OK_HTTP = "4.10.0" // Released: 12-06-22
            const val OK_HTTP_ALPHA = "5.0.0-alpha.10" // Released: 27-06-22

        }

        @Suppress("MemberNameEqualsClassName")
        object Gson {

            // Releases: https://github.com/google/gson/releases
            const val GSON = "2.9.1" // Released: 01-08-22

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
            const val GLIDE = "4.14.2" // Released: 07-10-22

        }

        @Suppress("MemberNameEqualsClassName")
        object Coil {

            // Releases: https://github.com/coil-kt/coil/releases
            const val COIL = "2.2.2" // Released: 02-10-22

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
            const val MOCK_K = "1.13.2" // Released: 28-09-22

        }

    }

}
