@Suppress("MayBeConstant", "MemberVisibilityCanBePrivate")
object Deps {

    object Plugin {

        val ANDROID = "com.android.tools.build:gradle:${Versions.Plugin.ANDROID}"
        val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugin.KOTLIN}"
        // TODO: Re-add Detekt plugin.
        val ANDROID_J_UNIT_5 = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.Plugin.ANDROID_J_UNIT_5}"

    }

    object LeakCanary {

        val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${Versions.LeakCanary.LEAK_CANARY}"

    }

    // IMPLEMENTATION // ****************************************************************************************************

    object Kotlin {

        object Core {

            val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Plugin.KOTLIN}"
            val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.Plugin.KOTLIN}"

        }

        object Coroutines {

            val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.COROUTINES}"
            val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINES}"

        }

    }

    object Material {

        val MATERIAL = "com.google.android.material:material:${Versions.Material.MATERIAL}"

    }

    object Android {

        object Core {

            val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.Android.Core.APP_COMPAT}"
            val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.Android.Core.RECYCLER_VIEW}"
            val CARD_VIEW = "androidx.cardview:cardview:${Versions.Android.Core.CARD_VIEW}"
            val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.Android.Core.CONSTRAINT_LAYOUT}"

        }

        object Ktx {

            val CORE = "androidx.core:core-ktx:${Versions.Android.Ktx.CORE}"

        }

        object Arch {

            object Core {

                val LIFECYCLE_EXTENSIONS =
                    "androidx.lifecycle:lifecycle-extensions:${Versions.Android.Arch.Core.LIFECYCLE_EXTENSIONS}"

            }

            object Test {

                val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.Android.Arch.Test.CORE_TESTING}"

            }

        }

        object Test {

            val CORE = "androidx.test:core:${Versions.Android.Test.CORE}"
            val J_UNIT = "androidx.test.ext:junit:${Versions.Android.Test.J_UNIT}"
            val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.Android.Test.ESPRESSO}"

            val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.Android.Test.ROBOLECTRIC}"

        }

    }

    object Di {

        object Koin {

            object Kotlin {

                val CORE = "org.koin:koin-core:${Versions.Di.Koin.KOIN}"

            }

            object Android {

                val ANDROID = "org.koin:koin-android:${Versions.Di.Koin.KOIN}"
                val VIEW_MODEL = "org.koin:koin-androidx-viewmodel:${Versions.Di.Koin.KOIN}"

            }

        }

    }

    object Net {

        object Rest {

            val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Rest.RETROFIT}"
            val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.Rest.RETROFIT}"

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

        object Assert {

            val STRIKT = "io.strikt:strikt-core:${Versions.Test.Assert.STRIKT}"

        }

        object Mock {

            val MOCK_K = "io.mockk:mockk:${Versions.Test.Mock.MOCK_K}"

        }

    }

}
