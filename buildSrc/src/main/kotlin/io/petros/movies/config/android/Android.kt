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
            const val CUSTOM_INSTRUMENTATION_RUNNER = "io.petros.movies.app.runner.CustomAndroidJunitRunner"

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

            const val AL = "$META_INF_DIR/AL2.0"
            const val LGPL = "$META_INF_DIR/LGPL2.1"

            const val KOTLIN_COROUTINES = "$META_INF_DIR/kotlinx-coroutines-core.kotlin_module"

        }

    }

}
