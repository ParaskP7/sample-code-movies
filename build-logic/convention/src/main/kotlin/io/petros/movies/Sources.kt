package io.petros.movies

object Sources {

    const val MAIN = "main"
    const val TEST = "test"
    const val ANDROID_TEST = "androidTest"

    object Folders {

        const val SRC = "src"

        const val MAIN = "main"
        const val TEST = "test"
        const val INTEGRATION_TEST = "integrationTest"
        const val ROBOLECTRIC_TEST = "robolectricTest"
        const val ANDROID_TEST = "androidTest"

        object Subfolder {

            const val KOTLIN = "kotlin"
            const val RESOURCES = "resources"

        }

    }

    object Main {

        const val KOTLIN = "${Folders.SRC}/${Folders.MAIN}/" +
                Folders.Subfolder.KOTLIN
        const val RESOURCES = "${Folders.SRC}/${Folders.MAIN}/" +
                Folders.Subfolder.RESOURCES

    }

    object Test {

        const val KOTLIN = "${Folders.SRC}/${Folders.TEST}/" +
                Folders.Subfolder.KOTLIN
        const val RESOURCES = "${Folders.SRC}/${Folders.TEST}/" +
                Folders.Subfolder.RESOURCES

    }

    object Integration {

        const val KOTLIN = "${Folders.SRC}/${Folders.INTEGRATION_TEST}/" +
                Folders.Subfolder.KOTLIN
        const val RESOURCES = "${Folders.SRC}/${Folders.INTEGRATION_TEST}/" +
                Folders.Subfolder.RESOURCES

    }

    object Robolectric {

        const val KOTLIN = "${Folders.SRC}/${Folders.ROBOLECTRIC_TEST}/" +
                Folders.Subfolder.KOTLIN
        const val RESOURCES = "${Folders.SRC}/${Folders.ROBOLECTRIC_TEST}/" +
                Folders.Subfolder.RESOURCES

    }

    object Android {

        object Test {

            const val KOTLIN = "${Folders.SRC}/${Folders.ANDROID_TEST}/" +
                    Folders.Subfolder.KOTLIN
            const val RESOURCES = "${Folders.SRC}/${Folders.ANDROID_TEST}/" +
                    Folders.Subfolder.RESOURCES

        }

    }

}
