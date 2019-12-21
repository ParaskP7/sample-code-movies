@Suppress("MayBeConstant", "MemberVisibilityCanBePrivate")
object Deps {

    object Plugin {

        val ANDROID = "com.android.tools.build:gradle:${Versions.Plugin.ANDROID}"
        val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugin.KOTLIN}"
        val VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${Versions.Plugin.VERSIONS}"
        val DEXCOUNT = "com.getkeepsafe.dexcount:dexcount-gradle-plugin:${Versions.Plugin.DEXCOUNT}"
        // TODO: Re-add Detekt plugin.
        val ANDROID_J_UNIT_5 = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.Plugin.ANDROID_J_UNIT_5}"

    }

    object LeakCanary {

        val GROUP = "com.squareup.leakcanary"
        val NAME = "leakcanary-android"

        val DEBUG = "${GROUP}:${NAME}:${Versions.LeakCanary.LEAK_CANARY}"
        val RELEASE = "${GROUP}:${NAME}-no-op:${Versions.LeakCanary.LEAK_CANARY}"

    }

    // IMPLEMENTATION // ****************************************************************************************************

    object Kotlin {

        object Coroutines {

            val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.COROUTINES}"
            val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINES}"

        }

        val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Plugin.KOTLIN}"
        val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.Plugin.KOTLIN}"

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

        object Koin {

            object Kotlin {

                val CORE = "org.koin:koin-core:${Versions.Di.KOIN}"

            }

            object Android {

                val ANDROID = "org.koin:koin-android:${Versions.Di.KOIN}"
                val VIEW_MODEL = "org.koin:koin-androidx-viewmodel:${Versions.Di.KOIN}"

            }

        }

    }

    object Net {

        object Rest {

            val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Rest.RETROFIT}"
            val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.Rest.RETROFIT}"
            val RETROFIT_COROUTINES = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.Rest.RETROFIT_COROUTINES}"

        }

        val OK_HTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.Net.OK_HTTP}"

    }

    object Image {

        val GLIDE = "com.github.bumptech.glide:glide:${Versions.Image.GLIDE}"
        val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.Image.GLIDE}"

    }

    object Util {

        val MONTH_YEAR_PICKER = "com.whiteelephant:monthandyearpicker:${Versions.Util.MONTH_YEAR_PICKER}"

    }

    object Log {

        val TIMBER = "com.jakewharton.timber:timber:${Versions.Log.TIMBER}"

    }

    // TEST IMPLEMENTATION // ***********************************************************************************************

    object Test {

        object Spek {

            val DSL = "org.spekframework.spek2:spek-dsl-jvm:${Versions.Test.Spek.SPEK}"
            val J_UNIT = "org.spekframework.spek2:spek-runner-junit5:${Versions.Test.Spek.SPEK}"

        }

        object JUnit {

            val J_UNIT = "junit:junit:${Versions.Test.JUnit.J_UNIT}"
            val J_UNIT_VINTAGE = "org.junit.vintage:junit-vintage-engine:${Versions.Test.JUnit.VINTAGE}"

        }

        val STRIKT = "io.strikt:strikt-core:${Versions.Test.STRIKT}"

    }

    object Mock {

        val MOCK_K = "io.mockk:mockk:${Versions.Mock.MOCK_K}"

    }

    object Robolectric {

        val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.Robolectric.ROBOLECTRIC}"

    }

}
