package io.petros.movies.config

import io.petros.movies.config.dirs.Folders

object Sources {

    const val MAIN = "main"
    const val TEST = "test"
    const val ANDROID_TEST = "androidTest"

    object Main {

        const val KOTLIN = "${Folders.Source.SRC}/${Folders.Source.MAIN}/" +
                Folders.Source.Subfolder.KOTLIN
        const val RESOURCES = "${Folders.Source.SRC}/${Folders.Source.MAIN}/" +
                Folders.Source.Subfolder.RESOURCES

    }

    object Test {

        const val KOTLIN = "${Folders.Source.SRC}/${Folders.Source.TEST}/" +
                Folders.Source.Subfolder.KOTLIN
        const val RESOURCES = "${Folders.Source.SRC}/${Folders.Source.TEST}/" +
                Folders.Source.Subfolder.RESOURCES

    }

    object Spek {

        const val KOTLIN = "${Folders.Source.SRC}/${Folders.Source.SPEK_TEST}/" +
                Folders.Source.Subfolder.KOTLIN
        const val RESOURCES = "${Folders.Source.SRC}/${Folders.Source.SPEK_TEST}/" +
                Folders.Source.Subfolder.RESOURCES

    }

    object Robolectric {

        const val KOTLIN = "${Folders.Source.SRC}/${Folders.Source.ROBOLECTRIC_TEST}/" +
                Folders.Source.Subfolder.KOTLIN
        const val RESOURCES = "${Folders.Source.SRC}/${Folders.Source.ROBOLECTRIC_TEST}/" +
                Folders.Source.Subfolder.RESOURCES

    }

    object Android {

        object Test {

            const val KOTLIN = "${Folders.Source.SRC}/${Folders.Source.ANDROID_TEST}/" +
                    Folders.Source.Subfolder.KOTLIN
            const val RESOURCES = "${Folders.Source.SRC}/${Folders.Source.ANDROID_TEST}/" +
                    Folders.Source.Subfolder.RESOURCES

        }

    }

}
