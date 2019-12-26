object Versions {

    object Plugin {

        // Release: https://androidstudio.googleblog.com
        const val ANDROID = "4.0.0-alpha07"
        // Release: https://blog.jetbrains.com/kotlin/category/releases
        const val KOTLIN = "1.3.61"
        // TODO: Re-add Detekt plugin.
        // Release: https://github.com/mannodermaus/android-junit5/releases
        const val ANDROID_J_UNIT_5 = "1.5.2.0"

    }

    object LeakCanary {

        // Release: https://github.com/square/leakcanary/releases
        const val LEAK_CANARY = "2.0"

    }

    // IMPLEMENTATION // ****************************************************************************************************

    object Kotlin {

        // Release: https://github.com/Kotlin/kotlinx.coroutines/releases
        const val COROUTINES = "1.3.3"

    }

    object Material {

        // Release: https://github.com/material-components/material-components-android/releases
        const val MATERIAL = "1.0.0"

    }

    object Android {

        object Ktx {

            // Release: https://developer.android.com/jetpack/androidx/releases/core
            const val CORE = "1.1.0"

        }

        object Arch {

            object Test {

                // Release: https://developer.android.com/jetpack/androidx/releases/arch
                const val CORE_TESTING = "2.1.0"

            }

            // Release: https://developer.android.com/jetpack/androidx/releases/lifecycle
            const val LIFECYCLE_EXTENSIONS = "2.1.0"

        }

        // Release: https://developer.android.com/jetpack/androidx/releases/test
        object Test {

            const val TEST_CORE = "1.2.0"
            const val TEST_J_UNIT = "1.1.1"
            const val TEST_ESPRESSO = "3.2.0"

        }

        // Release: https://developer.android.com/jetpack/androidx/releases/appcompat
        const val APP_COMPAT = "1.1.0"
        // Release: https://developer.android.com/jetpack/androidx/releases/recyclerview
        const val RECYCLER_VIEW = "1.1.0"
        // Release: https://developer.android.com/jetpack/androidx/releases/cardview
        const val CARD_VIEW = "1.0.0"
        // Release: https://developer.android.com/jetpack/androidx/releases/constraintlayout
        const val CONSTRAINT_LAYOUT = "1.1.3"

    }

    object Di {

        // Release: https://github.com/InsertKoinIO/koin/releases
        const val KOIN = "2.0.1"

    }

    object Net {

        // Release: https://github.com/square/okhttp/releases
        const val OK_HTTP = "4.2.2"

    }

    object Rest {

        // Release: https://github.com/square/retrofit/releases
        const val RETROFIT = "2.6.3"

    }

    object Image {

        // Release: https://github.com/bumptech/glide/releases
        const val GLIDE = "4.10.0"

    }

    object Util {

        // Release: https://mvnrepository.com/artifact/com.whiteelephant/monthandyearpicker
        const val MONTH_YEAR_PICKER = "1.3.0"

    }

    object Log {

        // Release: https://github.com/JakeWharton/timber/releases
        const val TIMBER = "4.7.1"

    }

    // TEST IMPLEMENTATION // ***********************************************************************************************

    object Test {

        object Spek {

            // Release: https://github.com/spekframework/spek/releases
            const val SPEK = "2.0.9"

        }

        object JUnit {

            // Release: https://github.com/junit-team/junit4/releases
            const val J_UNIT = "4.12"
            // Release: https://github.com/junit-team/junit5/releases
            const val VINTAGE = "5.5.2"

        }

        object Assert {

            // Release: https://github.com/robfletcher/strikt/releases
            const val STRIKT = "0.23.2"

        }

    }

    object Mock {

        // Release: https://github.com/mockk/mockk/releases
        const val MOCK_K = "1.9.3"

    }

    object Robolectric {

        // Release: https://github.com/robolectric/robolectric/releases
        const val ROBOLECTRIC = "4.3.1"

    }

}
