object Sources {

    const val MAIN = "main"
    const val TEST = "test"
    const val ANDROID_TEST = "androidTest"

    object Main {

        const val KOTLIN = "${Folders.Source.SRC}/${Folders.Source.MAIN}/${Folders.Source.KOTLIN}"

    }

    object Test {

        const val KOTLIN = "${Folders.Source.SRC}/${Folders.Source.TEST}/${Folders.Source.KOTLIN}"
        const val RESOURCES = "${Folders.Source.SRC}/${Folders.Source.TEST}/${Folders.Source.RESOURCES}"
        const val ROBOLECTRIC = "${Folders.Source.SRC}/${Folders.Source.ROBOLECTRIC_TEST}/${Folders.Source.KOTLIN}"

    }

    object Android {

        object Test {

            const val KOTLIN = "${Folders.Source.SRC}/${Folders.Source.ANDROID_TEST}/${Folders.Source.KOTLIN}"

        }

    }

}
