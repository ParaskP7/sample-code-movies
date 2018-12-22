object Deps {

    object Plugin {

        val ANDROID = "com.android.tools.build:gradle:${Versions.Plugin.ANDROID}"
        val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugin.KOTLIN}"
        val VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${Versions.Plugin.VERSIONS}"
        val DEXCOUNT = "com.getkeepsafe.dexcount:dexcount-gradle-plugin:${Versions.Plugin.DEXCOUNT}"
        val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.Plugin.DETEKT}"
        val DETEKT_FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.Plugin.DETEKT}"

    }

    object LeakCanary {

        val GROUP = "com.squareup.leakcanary"
        val NAME = "leakcanary-android"

        val DEBUG = "${LeakCanary.GROUP}:${LeakCanary.NAME}:${Versions.LeakCanary.LEAK_CANARY}"
        val RELEASE = "${LeakCanary.GROUP}:${LeakCanary.NAME}-no-op:${Versions.LeakCanary.LEAK_CANARY}"

    }

    // IMPLEMENTATION // ****************************************************************************************************

    object Kotlin {

        val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Plugin.KOTLIN}"

    }

    object Material {

        val MATERIAL = "com.google.android.material:material:${Versions.Material.MATERIAL}"

    }

    object Android {

        object Ktx {

            val CORE = "androidx.core:core-ktx:${Versions.Android.Ktx.CORE}"

        }

        object Arch {

            object Test {

                val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.Android.Arch.Test.CORE_TESTING}"

            }

            val LIFECYCLE_EXTENSIONS =
                "androidx.lifecycle:lifecycle-extensions:${Versions.Android.Arch.LIFECYCLE_EXTENSIONS}"

        }

        object Test {

            val CORE = "androidx.test:core:${Versions.Android.Test.TEST_CORE}"
            val J_UNIT = "androidx.test.ext:junit:${Versions.Android.Test.TEST_J_UNIT}"
            val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.Android.Test.TEST_ESPRESSO}"

        }

        val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.Android.APP_COMPAT}"
        val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.Android.RECYCLER_VIEW}"
        val CARD_VIEW = "androidx.cardview:cardview:${Versions.Android.CARD_VIEW}"
        val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.Android.CONSTRAINT_LAYOUT}"

    }

    object Di {

        val DAGGER = "com.google.dagger:dagger:${Versions.Di.DAGGER}"
        val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.Di.DAGGER}"
        val DAGGER_ANDROID = "com.google.dagger:dagger-android-support:${Versions.Di.DAGGER}"
        val DAGGER_ANDROID_PROCESSOR = "com.google.dagger:dagger-android-processor:${Versions.Di.DAGGER}"

    }

    object Rx {

        val RX_JAVA = "io.reactivex.rxjava2:rxjava:${Versions.Rx.RX_JAVA}"
        val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.RX_ANDROID}"

    }

    object Net {

        object Rest {

            val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Rest.RETROFIT}"
            val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.Rest.RETROFIT}"
            val RETROFIT_RX = "com.squareup.retrofit2:adapter-rxjava2:${Versions.Rest.RETROFIT}"

        }

        val GSON = "com.google.code.gson:gson:${Versions.Net.GSON}"
        val OK_HTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.Net.OK_HTTP}"

    }

    object Image {

        val GLIDE = "com.github.bumptech.glide:glide:${Versions.Image.GLIDE}"
        val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.Image.GLIDE}"

    }

    object Extras {

        val DART = "com.f2prateek.dart:dart:${Versions.Extras.DART}"
        val DART_PROCESSOR = "com.f2prateek.dart:dart-processor:${Versions.Extras.DART}"
        val HENSON = "com.f2prateek.dart:henson:${Versions.Extras.DART}"
        val HENSON_PROCESSOR = "com.f2prateek.dart:henson-processor:${Versions.Extras.DART}"

    }

    object Util {

        val MONTH_YEAR_PICKER = "com.whiteelephant:monthandyearpicker:${Versions.Util.MONTH_YEAR_PICKER}"

    }

    object Log {

        val TIMBER = "com.jakewharton.timber:timber:${Versions.Log.TIMBER}"

    }

    // TEST IMPLEMENTATION // ***********************************************************************************************

    object Test {

        val J_UNIT = "junit:junit:${Versions.Test.J_UNIT}"
        val ASSERT_J = "org.assertj:assertj-core:${Versions.Test.ASSERT_J}"

    }

    object Mock {

        val MOCKITO_KOTLIN = "com.nhaarman:mockito-kotlin:${Versions.Mock.MOCKITO_KOTLIN}"

    }

    object Robolectric {

        val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.Robolectric.ROBOLECTRIC}"

    }

}
