package io.petros.movies.config.deps

@Suppress("StringLiteralDuplication")
object Versions {

    // DEBUG IMPLEMENTATION // **********************************************************************************************

    object LeakCanary {

        // Releases: https://github.com/square/leakcanary/releases
        const val LEAK_CANARY = "2.6" // Released: 25-12-20

    }

    // IMPLEMENTATION // ****************************************************************************************************

    object Kotlin {

        // Releases: https://github.com/Kotlin/kotlinx.coroutines/releases
        const val COROUTINES = "1.4.2" // Released: 26-11-20

    }

    @Suppress("MemberNameEqualsClassName")
    object Material {

        // Releases: https://github.com/material-components/material-components-android/releases
        const val MATERIAL = "1.3.0" // Released: 04-02-21

    }

    object Android {

        object Core {

            // Releases: https://developer.android.com/jetpack/androidx/releases/appcompat
            @Suppress("unused") const val APP_COMPAT = "1.2.0" // Released: 05-08-20
            const val APP_COMPAT_BETA = "1.3.0-beta01" // Released: 13-01-21

            // Releases: https://developer.android.com/jetpack/androidx/releases/fragment
            const val FRAGMENT = "1.3.0" // Released: 10-02-21

            // Releases: https://developer.android.com/jetpack/androidx/releases/recyclerview
            @Suppress("unused") const val RECYCLER_VIEW = "1.1.0" // Released: 20-11-19
            const val RECYCLER_VIEW_BETA = "1.2.0-beta01" // Released: 02-12-19

            // Releases: https://developer.android.com/jetpack/androidx/releases/constraintlayout
            const val CONSTRAINT_LAYOUT = "2.0.4" // Released: 29-10-20
            @Suppress("unused") const val CONSTRAINT_LAYOUT_ALPHA = "2.1.0-alpha2" // Released: 17-12-20

            // Releases: https://developer.android.com/jetpack/androidx/releases/drawerlayout
            const val DRAWER_LAYOUT = "1.1.1" // Released: 02-09-20

            // Releases: https://developer.android.com/jetpack/androidx/releases/coordinatorlayout
            const val COORDINATOR_LAYOUT = "1.1.0" // Released: 04-12-19

        }

        object Ktx {

            // Releases: https://developer.android.com/jetpack/androidx/releases/core
            @Suppress("unused") const val CORE = "1.3.2" // Released: 01-10-20
            const val CORE_BETA = "1.5.0-beta01" // Released: 13-01-21

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
                const val LIFECYCLE = "2.3.0" // Released: 10-02-20

            }

            @Suppress("MemberNameEqualsClassName")
            object SavedState {

                // Releases: https://developer.android.com/jetpack/androidx/releases/savedstate
                const val SAVED_STATE = "1.1.0" // Released: 10-02-20

            }

            @Suppress("MemberNameEqualsClassName")
            object Navigation {

                // Releases: https://developer.android.com/jetpack/androidx/releases/navigation
                const val NAVIGATION = "2.3.3" // Released: 27-01-21

            }

            object CustomView {

                // Releases: https://developer.android.com/jetpack/androidx/releases/customview
                const val CUSTOM_VIEW = "1.1.0" // Release: 24-06-20

            }

            object Database {

                @Suppress("MemberNameEqualsClassName")
                object SQLight {

                    // Releases: https://developer.android.com/jetpack/androidx/releases/sqlite
                    const val SQLIGHT = "2.1.0" // Release: 22-01-20

                }

                @Suppress("MemberNameEqualsClassName")
                object Room {

                    // Releases: https://developer.android.com/jetpack/androidx/releases/room
                    @Suppress("unused") const val ROOM = "2.2.6" // Release: 16-12-20
                    const val ROOM_ALPHA = "2.3.0-beta02" // Release: 18-02-21

                }

            }

            @Suppress("MemberNameEqualsClassName")
            object Pagination {

                // Releases: https://developer.android.com/jetpack/androidx/releases/paging
                @Suppress("unused") const val PAGING = "2.1.2" // Release: 18-03-20
                const val PAGING_BETA = "3.0.0-beta01" // Release: 10-02-21

            }

        }

        object Test {

            // Releases: https://developer.android.com/jetpack/androidx/releases/test
            const val CORE = "1.3.0" // Released: 25-08-20
            const val ESPRESSO = "3.3.0" // Released: 25-08-20
            const val J_UNIT = "1.1.2" // Released: 25-08-20

            // Releases: https://github.com/robolectric/robolectric/releases
            const val ROBOLECTRIC = "4.5.1" // Released: 01-02-21

        }

    }

    object Architecture {

        object Mvi {

            @Suppress("MemberNameEqualsClassName")
            object Stateful {

                // Releases: https://github.com/iFanie/Stateful/releases
                const val STATEFUL = "0.4.0" // 17-06-20

            }

        }

    }

    object Di {

        @Suppress("MemberNameEqualsClassName")
        object Koin {

            // Releases: https://github.com/InsertKoinIO/koin/releases
            const val KOIN = "2.2.2" // Released: 19-12-20

        }

    }

    object Net {

        object OkHttp {

            // Releases: https://github.com/square/okhttp/releases
            const val OK_HTTP = "4.9.1" // Released: 30-01-21

        }

        @Suppress("MemberNameEqualsClassName")
        object Gson {

            // Releases: https://github.com/google/gson/releases
            const val GSON = "2.8.6" // Released: 04-10-19

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
            const val GLIDE = "4.12.0" // Released: 29-01-21

        }

    }

    object Util {

        // Releases: https://mvnrepository.com/artifact/com.whiteelephant/monthandyearpicker
        const val MONTH_YEAR_PICKER = "1.3.0" // Released: 04-09-18

    }

    object Log {

        // Releases: https://github.com/JakeWharton/timber/releases
        const val TIMBER = "4.7.1" // Released: 28-06-18

    }

    // TEST IMPLEMENTATION // ***********************************************************************************************

    object Test {

        @Suppress("MemberNameEqualsClassName")
        object Spek {

            // Releases: https://github.com/spekframework/spek/releases
            const val SPEK = "2.0.15" // Released: 14-12-20

        }

        object JUnit {

            // Releases: https://github.com/junit-team/junit4/releases
            const val J_UNIT_4 = "4.13.2" // Released: 13-02-21

            // Releases: https://github.com/junit-team/junit5/releases
            const val J_UNIT_5 = "5.7.1" // Released: 04-02-21

        }

        object Assert {

            // Releases: https://github.com/robfletcher/strikt/releases
            const val STRIKT = "0.29.0" // Released: 07-02-21

        }

        object Mock {

            // Releases: https://github.com/mockk/mockk/releases
            const val MOCK_K = "1.10.6" // Released: 13-02-21

        }

    }

}
