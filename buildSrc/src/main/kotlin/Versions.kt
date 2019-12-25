object Versions {

    object Plugin {

        const val ANDROID = "4.0.0-alpha07"
        const val KOTLIN = "1.3.31"
        // TODO: Re-add Detekt plugin.
        const val ANDROID_J_UNIT_5 = "1.4.0.0"

    }

    object LeakCanary {

        const val LEAK_CANARY = "1.6.3"

    }

    // IMPLEMENTATION // ****************************************************************************************************

    object Kotlin {

        const val COROUTINES = "1.1.1"

    }

    object Material {

        const val MATERIAL = "1.0.0"

    }

    object Android {

        object Ktx {

            const val CORE = "1.0.1"

        }

        object Arch {

            object Test {

                const val CORE_TESTING = "2.0.0"

            }

            const val LIFECYCLE_EXTENSIONS = "2.0.0"

        }

        object Test {

            const val TEST_CORE = "1.1.0"
            const val TEST_J_UNIT = "1.1.0"
            const val TEST_ESPRESSO = "3.1.1"

        }

        const val APP_COMPAT = "1.0.2"
        const val RECYCLER_VIEW = "1.0.0"
        const val CARD_VIEW = "1.0.0"
        const val CONSTRAINT_LAYOUT = "1.1.3"

    }

    object Di {

        const val KOIN = "1.0.2"

    }

    object Net {

        const val OK_HTTP = "3.14.0"

    }

    object Rest {

        const val RETROFIT = "2.5.0"
        const val RETROFIT_COROUTINES = "0.9.2"

    }

    object Image {

        const val GLIDE = "4.9.0"

    }

    object Util {

        const val MONTH_YEAR_PICKER = "1.3.0"

    }

    object Log {

        const val TIMBER = "4.7.1"

    }

    // TEST IMPLEMENTATION // ***********************************************************************************************

    object Test {

        object Spek {

            const val SPEK = "2.0.1"

        }

        object JUnit {

            const val J_UNIT = "4.12"
            const val VINTAGE = "5.4.0"

        }

        const val STRIKT = "0.19.2"

    }

    object Mock {

        const val MOCK_K = "1.9.2"

    }

    object Robolectric {

        const val ROBOLECTRIC = "4.2.1"

    }

}
