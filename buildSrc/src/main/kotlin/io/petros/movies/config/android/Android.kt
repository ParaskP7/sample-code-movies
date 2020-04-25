package io.petros.movies.config.android

object Android {

    object Sdk {

        const val MIN = 21
        const val TARGET = 29
        const val COMPILE = 29

    }

    object DefaultConfig {

        object Test {

            @Suppress("unused")
            const val DEFAULT_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
            const val CUSTOM_INSTRUMENTATION_RUNNER = "io.petros.movies.android_test.runner.CustomAndroidJunitRunner"

        }

    }

    object BuildTypes {

        const val DEBUG = "debug"
        const val RELEASE = "release"

    }

    object PackagingOption {

        object Exclude {

            private const val META_INF_DIR = "META-INF"
            const val LICENCE = "$META_INF_DIR/LICENSE.md"
            const val LICENCE_NOTICE = "$META_INF_DIR/LICENSE-notice.md"

        }

    }

}
