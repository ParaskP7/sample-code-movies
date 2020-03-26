@file:Suppress("InvalidPackageDeclaration")

@Suppress("StringLiteralDuplication")
object Versions {

    object Plugin {

        // Releases: https://androidstudio.googleblog.com
        const val ANDROID = "4.1.0-alpha04" // Released: 24-03-20

        // Releases: https://blog.jetbrains.com/kotlin/category/releases
        const val KOTLIN = "1.3.71" // Released: 23-03-20

        // Releases: https://github.com/arturbosch/detekt/releases
        const val DETEKT = "1.7.1" // Released: 26-03-20

        // Releases: https://github.com/mannodermaus/android-junit5/releases
        const val ANDROID_J_UNIT_5 = "1.6.0.0" // Released: 28-02-20

    }

    object LeakCanary {

        // Releases: https://github.com/square/leakcanary/releases
        const val LEAK_CANARY = "2.2" // Released: 06-02-20

    }

    // IMPLEMENTATION // ****************************************************************************************************

    object Kotlin {

        // Releases: https://github.com/Kotlin/kotlinx.coroutines/releases
        const val COROUTINES = "1.3.5" // Released: 17-03-20

    }

    @Suppress("MemberNameEqualsClassName")
    object Material {

        // Releases: https://github.com/material-components/material-components-android/releases
        const val MATERIAL = "1.1.0" // Released: 03-02-20

    }

    object Android {

        object Core {

            // Releases: https://developer.android.com/jetpack/androidx/releases/appcompat
            const val APP_COMPAT = "1.1.0" // Released: 04-03-20

            // Releases: https://developer.android.com/jetpack/androidx/releases/recyclerview
            const val RECYCLER_VIEW = "1.1.0" // Released: 05-02-20

            // Releases: https://developer.android.com/jetpack/androidx/releases/cardview
            const val CARD_VIEW = "1.0.0" // Released: 21-09-18

            // Releases: https://developer.android.com/jetpack/androidx/releases/constraintlayout
            const val CONSTRAINT_LAYOUT = "1.1.3" // Released: 16-12-19

        }

        object Ktx {

            // Releases: https://developer.android.com/jetpack/androidx/releases/core
            const val CORE = "1.2.0" // Released: 04-03-20

        }

        object Arch {

            object Core {

                object Lifecycle {

                    // Releases: https://developer.android.com/jetpack/androidx/releases/lifecycle
                    const val EXTENSIONS = "2.1.0"

                }

            }

            object Test {

                // Releases: https://developer.android.com/jetpack/androidx/releases/arch
                const val CORE_TESTING = "2.1.0"

            }

        }

        object Test {

            // Releases: https://developer.android.com/jetpack/androidx/releases/test
            const val CORE = "1.2.0"

            // Releases: https://developer.android.com/jetpack/androidx/releases/test
            const val J_UNIT = "1.1.1"

            // Releases: https://developer.android.com/jetpack/androidx/releases/test
            const val ESPRESSO = "3.2.0"

            // Releases: https://github.com/robolectric/robolectric/releases
            const val ROBOLECTRIC = "4.3.1"

        }

    }

    object Di {

        @Suppress("MemberNameEqualsClassName")
        object Koin {

            // Releases: https://github.com/InsertKoinIO/koin/releases
            const val KOIN = "2.0.1"

        }

    }

    object Net {

        object OkHttp {

            // Releases: https://github.com/square/okhttp/releases
            const val OK_HTTP = "4.2.2"

        }

        object Rest {

            // Releases: https://github.com/square/retrofit/releases
            const val RETROFIT = "2.6.3"

        }

    }

    object Image {

        @Suppress("MemberNameEqualsClassName")
        object Glide {

            // Releases: https://github.com/bumptech/glide/releases
            const val GLIDE = "4.10.0"

        }

    }

    object Util {

        // Releases: https://mvnrepository.com/artifact/com.whiteelephant/monthandyearpicker
        const val MONTH_YEAR_PICKER = "1.3.0"

    }

    object Log {

        // Releases: https://github.com/JakeWharton/timber/releases
        const val TIMBER = "4.7.1"

    }

    // TEST IMPLEMENTATION // ***********************************************************************************************

    object Test {

        @Suppress("MemberNameEqualsClassName")
        object Spek {

            // Releases: https://github.com/spekframework/spek/releases
            const val SPEK = "2.0.9"

        }

        object JUnit {

            // Releases: https://github.com/junit-team/junit4/releases
            const val J_UNIT_4 = "4.12"

            // Releases: https://github.com/junit-team/junit5/releases
            const val J_UNIT_5 = "5.6.1"

        }

        object Assert {

            // Releases: https://github.com/robfletcher/strikt/releases
            const val STRIKT = "0.23.2"

        }

        object Mock {

            // Releases: https://github.com/mockk/mockk/releases
            const val MOCK_K = "1.9.3"

        }

    }

}
