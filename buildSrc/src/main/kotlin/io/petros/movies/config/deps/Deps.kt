package io.petros.movies.config.deps

import Plugins
import io.petros.movies.config.utils.Utils

@Suppress("MayBeConstant", "MemberVisibilityCanBePrivate")
object Deps {

    // DEBUG IMPLEMENTATION // **********************************************************************************************

    object LeakCanary {

        val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${Versions.LeakCanary.LEAK_CANARY}"
        val PLUMBER = "com.squareup.leakcanary:plumber-android:${Versions.LeakCanary.LEAK_CANARY}"

    }

    // IMPLEMENTATION // ****************************************************************************************************

    object Kotlin {

        object Core {

            val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${Plugins.Version.KOTLIN}"
            val KOTLIN_JDK8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Plugins.Version.KOTLIN}"
            val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Plugins.Version.KOTLIN}"

        }

        object Coroutines {

            val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.COROUTINES}"
            val CORE_JVM = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.Kotlin.COROUTINES}"
            val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINES}"

            @Suppress("MemberNameEqualsClassName")
            object Test {

                val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.COROUTINES}"

            }

        }

    }

    @Suppress("MemberNameEqualsClassName")
    object Material {

        val MATERIAL = "com.google.android.material:material:${Versions.Material.MATERIAL}"

    }

    object Android {

        object Core {

            val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.Android.Core.APP_COMPAT_BETA}"
            val FRAGMENT = "androidx.fragment:fragment:${Versions.Android.Core.FRAGMENT}"
            val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.Android.Core.RECYCLER_VIEW_BETA}"
            val CONSTRAINT_LAYOUT =
                "androidx.constraintlayout:constraintlayout:${Versions.Android.Core.CONSTRAINT_LAYOUT}"
            val DRAWER_LAYOUT = "androidx.drawerlayout:drawerlayout:${Versions.Android.Core.DRAWER_LAYOUT}"
            val COORDINATOR_LAYOUT =
                "androidx.coordinatorlayout:coordinatorlayout:${Versions.Android.Core.COORDINATOR_LAYOUT}"

        }

        object Ktx {

            val CORE = "androidx.core:core-ktx:${Versions.Android.Ktx.CORE_BETA}"

        }

        object Arch {

            object Core {

                val RUNTIME = "androidx.arch.core:core-runtime:${Versions.Android.Arch.Core.CORE}"

                val TESTING = "androidx.arch.core:core-testing:${Versions.Android.Arch.Core.CORE}"

            }

            object Lifecycle {

                val COMMON =
                    "androidx.lifecycle:lifecycle-common:${Versions.Android.Arch.Lifecycle.LIFECYCLE}"
                val COMMON_JAVA_8 =
                    "androidx.lifecycle:lifecycle-common-java8:${Versions.Android.Arch.Lifecycle.LIFECYCLE}"

                val RUNTIME_KTX =
                    "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.Arch.Lifecycle.LIFECYCLE}"

                val LIVE_DATA_CORE =
                    "androidx.lifecycle:lifecycle-livedata-core:${Versions.Android.Arch.Lifecycle.LIFECYCLE}"

                val VIEW_MODEL =
                    "androidx.lifecycle:lifecycle-viewmodel:${Versions.Android.Arch.Lifecycle.LIFECYCLE}"
                val VIEW_MODEL_KTX =
                    "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.Arch.Lifecycle.LIFECYCLE}"

                val PROCESS =
                    "androidx.lifecycle:lifecycle-process:${Versions.Android.Arch.Lifecycle.LIFECYCLE}"

            }

            object SavedState {

                val SAVED_STATE = "androidx.savedstate:savedstate:${Versions.Android.Arch.SavedState.SAVED_STATE}"

            }

            object Navigation {

                val COMMON =
                    "androidx.navigation:navigation-common:${Versions.Android.Arch.Navigation.NAVIGATION}"
                val COMMON_KTX =
                    "androidx.navigation:navigation-common-ktx:${Versions.Android.Arch.Navigation.NAVIGATION}"

                val RUNTIME =
                    "androidx.navigation:navigation-runtime:${Versions.Android.Arch.Navigation.NAVIGATION}"
                val RUNTIME_KTX =
                    "androidx.navigation:navigation-runtime-ktx:${Versions.Android.Arch.Navigation.NAVIGATION}"

                val UI =
                    "androidx.navigation:navigation-ui:${Versions.Android.Arch.Navigation.NAVIGATION}"
                val UI_KTX =
                    "androidx.navigation:navigation-ui-ktx:${Versions.Android.Arch.Navigation.NAVIGATION}"

                val FRAGMENT_KTX =
                    "androidx.navigation:navigation-fragment-ktx:${Versions.Android.Arch.Navigation.NAVIGATION}"

            }

            object CustomView {

                val CUSTOM_VIEW = "androidx.customview:customview:${Versions.Android.Arch.CustomView.CUSTOM_VIEW}"

            }

            object Database {

                @Suppress("MemberNameEqualsClassName")
                object SQLight {

                    val SQLIGHT = "androidx.sqlite:sqlite:${Versions.Android.Arch.Database.SQLight.SQLIGHT}"

                }

                object Room {

                    val COMMON = "androidx.room:room-common:${Versions.Android.Arch.Database.Room.ROOM_ALPHA}"

                    val RUNTIME = "androidx.room:room-runtime:${Versions.Android.Arch.Database.Room.ROOM_ALPHA}"

                    val KTX = "androidx.room:room-ktx:${Versions.Android.Arch.Database.Room.ROOM_ALPHA}"

                    val COMPILER = "androidx.room:room-compiler:${Versions.Android.Arch.Database.Room.ROOM_ALPHA}"

                }

            }

            object Pagination {

                val COMMON = "androidx.paging:paging-common:${Versions.Android.Arch.Pagination.PAGING_BETA}"

                val RUNTIME = "androidx.paging:paging-runtime:${Versions.Android.Arch.Pagination.PAGING_BETA}"

            }

        }

        object Test {

            val CORE = "androidx.test:core:${Versions.Android.Test.CORE_ALPHA}"
            val CORE_KTX = "androidx.test:core-ktx:${Versions.Android.Test.CORE_ALPHA}"
            val J_UNIT = "androidx.test.ext:junit:${Versions.Android.Test.J_UNIT_ALPHA}"
            val RUNNER = "androidx.test:runner:${Versions.Android.Test.CORE_ALPHA}"
            val RULES = "androidx.test:rules:${Versions.Android.Test.CORE_ALPHA}"

            object Espresso {

                val CORE = "androidx.test.espresso:espresso-core:${Versions.Android.Test.ESPRESSO_ALPHA}"
                val CONTRIB = "androidx.test.espresso:espresso-contrib:${Versions.Android.Test.ESPRESSO_ALPHA}"

                object Exclude {

                    const val HAMCREST = "org.hamcrest"

                }

            }

            @Suppress("MemberNameEqualsClassName")
            object Robolectric {

                val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.Android.Test.ROBOLECTRIC}"
                val ANNOTATIONS = "org.robolectric:annotations:${Versions.Android.Test.ROBOLECTRIC}"

                object Exclude {

                    const val MAVEN = "org.apache.maven"

                }

            }

        }

    }

    object Architecture {

        object Mvi {

            @Suppress("MemberNameEqualsClassName")
            object Stateful {

                val RUNTIME = "dev.fanie:stateful:${Versions.Architecture.Mvi.Stateful.STATEFUL}"
                val COMPILER = "dev.fanie:stateful-compiler:${Versions.Architecture.Mvi.Stateful.STATEFUL}"

                object Exclude {

                    const val KOTLIN = "org.jetbrains.kotlin"

                }

            }

        }

    }

    object Di {

        object Koin {

            object Kotlin {

                val CORE = "org.koin:koin-core:${Versions.Di.Koin.KOIN}"

            }

            @Suppress("MemberNameEqualsClassName")
            object Android {

                val ANDROID = "org.koin:koin-android:${Versions.Di.Koin.KOIN}"
                val VIEW_MODEL = "org.koin:koin-androidx-viewmodel:${Versions.Di.Koin.KOIN}"

            }

        }

    }

    object Net {

        object OkHttp {

            val OK_HTTP = "com.squareup.okhttp3:okhttp:${Versions.Net.OkHttp.OK_HTTP}"
            val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.Net.OkHttp.OK_HTTP}"

        }

        @Suppress("MemberNameEqualsClassName")
        object Gson {

            val GSON = "com.google.code.gson:gson:${Versions.Net.Gson.GSON}"

        }

        object Rest {

            val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Net.Rest.RETROFIT}"
            val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.Net.Rest.RETROFIT}"

        }

    }

    object Image {

        @Suppress("MemberNameEqualsClassName")
        object Glide {

            val GLIDE = "com.github.bumptech.glide:glide:${Versions.Image.Glide.GLIDE}"

        }

    }

    object Util {

        val MONTH_YEAR_PICKER = "com.whiteelephant:monthandyearpicker:${Versions.Util.MONTH_YEAR_PICKER}"

    }

    object Log {

        val TIMBER = "com.jakewharton.timber:timber:${Versions.Log.TIMBER}"

    }

    // TEST IMPLEMENTATION // ***********************************************************************************************

    object Test {

        object JUnit {

            val J_UNIT_4 = "junit:junit:${Versions.Test.JUnit.J_UNIT_4}"
            val J_UNIT_5 = "org.junit.vintage:junit-vintage-engine:${Versions.Test.JUnit.J_UNIT_5}"

        }

        object Spek {

            val DSL = "org.spekframework.spek2:spek-dsl-jvm:${Versions.Test.Spek.SPEK}"
            val J_UNIT_5 = "org.spekframework.spek2:spek-runner-junit5:${Versions.Test.Spek.SPEK}"

        }

        object Integration {

            val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.Net.OkHttp.OK_HTTP}"

        }

        object Hamcrest {

            val ALL = "org.hamcrest:hamcrest-all:${Versions.Test.Hamcrest.HAMCREST}"

        }

        object Assert {

            val STRIKT = "io.strikt:strikt-core:${Versions.Test.Assert.STRIKT}"

            object Exclude {

                const val KOTLIN = "org.jetbrains.kotlin"

            }

        }

        object Mock {

            val MOCK_K = "io.mockk:mockk:${Versions.Test.Mock.MOCK_K}"

        }

    }

}

@Suppress("unused")
fun String.group() = this.split(Utils.COLON)[0]

@Suppress("unused")
fun String.name() = this.split(Utils.COLON)[1]

fun String.identifier(): String {
    val groupNameVersion = this.split(Utils.COLON)
    return groupNameVersion[0] + Utils.COLON + groupNameVersion[1]
}
