@file:Suppress("InvalidPackageDeclaration")

object Android {

    object Sdk {

        const val MIN = 21
        const val TARGET = 29
        const val COMPILE = 29

    }

    object DefaultConfig {

        object Test {

            const val INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

        }

    }

    object BuildTypes {

        const val DEBUG = "debug"
        const val RELEASE = "release"

    }

}
