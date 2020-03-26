@file:Suppress("InvalidPackageDeclaration")

object Folders {

    const val PARENT = ".."

    object Config {

        const val CONFIG = "config"

        object Subfolder {

            const val KEYS = "keys"
            const val QUALITY = "quality"

        }

    }

    object Source {

        const val SRC = "src"

        const val MAIN = "main"
        const val TEST = "test"
        const val SPEK_TEST = "spekTest"
        const val ROBOLECTRIC_TEST = "robolectricTest"
        const val ANDROID_TEST = "androidTest"

        object Subfolder {

            const val KOTLIN = "kotlin"
            const val RESOURCES = "resources"

        }

    }

}
