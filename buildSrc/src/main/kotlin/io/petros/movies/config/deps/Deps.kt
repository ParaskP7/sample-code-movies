package io.petros.movies.config.deps

import Plugins
import io.petros.movies.config.utils.Utils

@Suppress("MayBeConstant", "MemberVisibilityCanBePrivate")
object Deps {

    // DEBUG IMPLEMENTATION // *****************************************************************************************

    object LeakCanary {

        val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${Versions.LeakCanary.LEAK_CANARY}"
        val PLUMBER = "com.squareup.leakcanary:plumber-android:${Versions.LeakCanary.LEAK_CANARY}"

    }

    // IMPLEMENTATION // ***********************************************************************************************

    object Kotlin {

        object Core {

            val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${Plugins.Version.KOTLIN_JETPACK_COMPOSE}"
            val KOTLIN_JDK8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Plugins.Version.KOTLIN_JETPACK_COMPOSE}"
            val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Plugins.Version.KOTLIN_JETPACK_COMPOSE}"

        }

        object Coroutines {

            val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.COROUTINES}"
            val CORE_JVM = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.Kotlin.COROUTINES}"
            val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINES}"

            @Suppress("MemberNameEqualsClassName")
            object Test {

                val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.COROUTINES}"
                val TEST_JVM = "org.jetbrains.kotlinx:kotlinx-coroutines-test-jvm:${Versions.Kotlin.COROUTINES}"

            }

        }

    }

    @Suppress("MemberNameEqualsClassName")
    object Material {

        val MATERIAL = "com.google.android.material:material:${Versions.Material.MATERIAL}"

    }

    object Android {

        object Core {

            val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.Android.Core.APP_COMPAT}"
            val APP_COMPAT_RESOURCES = "androidx.appcompat:appcompat-resources:${Versions.Android.Core.APP_COMPAT}"
            val FRAGMENT = "androidx.fragment:fragment:${Versions.Android.Core.FRAGMENT}"
            val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.Android.Core.RECYCLER_VIEW}"
            val CONSTRAINT_LAYOUT =
                "androidx.constraintlayout:constraintlayout:${Versions.Android.Core.CONSTRAINT_LAYOUT}"
            val DRAWER_LAYOUT = "androidx.drawerlayout:drawerlayout:${Versions.Android.Core.DRAWER_LAYOUT}"
            val COORDINATOR_LAYOUT =
                "androidx.coordinatorlayout:coordinatorlayout:${Versions.Android.Core.COORDINATOR_LAYOUT}"

        }

        object Ktx {

            val CORE = "androidx.core:core-ktx:${Versions.Android.Ktx.CORE}"

        }

        @Suppress("MemberNameEqualsClassName")
        object Compose {

            object Runtime {

                val RUNTIME = "androidx.compose.runtime:runtime:${Versions.Android.Compose.COMPOSE}"

            }

            object UI {

                val UI = "androidx.compose.ui:ui:${Versions.Android.Compose.COMPOSE}"
                val GRAPHICS = "androidx.compose.ui:ui-graphics:${Versions.Android.Compose.COMPOSE}"
                val TEXT = "androidx.compose.ui:ui-text:${Versions.Android.Compose.COMPOSE}"
                val UNIT = "androidx.compose.ui:ui-unit:${Versions.Android.Compose.COMPOSE}"
                val TOOLING = "androidx.compose.ui:ui-tooling:${Versions.Android.Compose.COMPOSE}"

            }

            object Foundation {

                val FOUNDATION = "androidx.compose.foundation:foundation:${Versions.Android.Compose.COMPOSE}"
                val LAYOUT = "androidx.compose.foundation:foundation-layout:${Versions.Android.Compose.COMPOSE}"

            }

            object Material {

                val MATERIAL = "androidx.compose.material:material:${Versions.Android.Compose.COMPOSE}"

            }

            object ConstraintLayout {

                val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:" +
                        Versions.Android.Compose.ConstraintLayout.CONSTRAINT_LAYOUT
            }

        }

        object Arch {

            object Core {

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

                val SAVED_STATE =
                    "androidx.savedstate:savedstate:${Versions.Android.Arch.SavedState.SAVED_STATE}"

            }

            object Navigation {

                val COMMON =
                    "androidx.navigation:navigation-common:${Versions.Android.Arch.Navigation.NAVIGATION}"

                val RUNTIME =
                    "androidx.navigation:navigation-runtime:${Versions.Android.Arch.Navigation.NAVIGATION}"
                val RUNTIME_KTX =
                    "androidx.navigation:navigation-runtime-ktx:${Versions.Android.Arch.Navigation.NAVIGATION}"

                val UI =
                    "androidx.navigation:navigation-ui:${Versions.Android.Arch.Navigation.NAVIGATION}"

                val FRAGMENT =
                    "androidx.navigation:navigation-fragment:${Versions.Android.Arch.Navigation.NAVIGATION}"
                val FRAGMENT_KTX =
                    "androidx.navigation:navigation-fragment-ktx:${Versions.Android.Arch.Navigation.NAVIGATION}"

            }

            object CustomView {

                val CUSTOM_VIEW = "androidx.customview:customview:${Versions.Android.Arch.CustomView.CUSTOM_VIEW}"

            }

            object DataStore {

                val CORE = "androidx.datastore:datastore-core:${Versions.Android.Arch.DataStore.DATASTORE}"

                @Suppress("MemberNameEqualsClassName")
                object Preferences {

                    val CORE =
                        "androidx.datastore:datastore-preferences-core:${Versions.Android.Arch.DataStore.DATASTORE}"
                    val PREFERENCES =
                        "androidx.datastore:datastore-preferences:${Versions.Android.Arch.DataStore.DATASTORE}"

                }

            }

            object Database {

                @Suppress("MemberNameEqualsClassName")
                object SQLight {

                    val SQLIGHT = "androidx.sqlite:sqlite:${Versions.Android.Arch.Database.SQLight.SQLIGHT}"

                }

                object Room {

                    val COMMON = "androidx.room:room-common:${Versions.Android.Arch.Database.Room.ROOM}"

                    val RUNTIME = "androidx.room:room-runtime:${Versions.Android.Arch.Database.Room.ROOM}"

                    val KTX = "androidx.room:room-ktx:${Versions.Android.Arch.Database.Room.ROOM}"

                    val PAGING = "androidx.room:room-paging:${Versions.Android.Arch.Database.Room.ROOM}"

                    val COMPILER = "androidx.room:room-compiler:${Versions.Android.Arch.Database.Room.ROOM}"

                }

            }

            object Pagination {

                val COMMON = "androidx.paging:paging-common:${Versions.Android.Arch.Pagination.PAGING}"

                val RUNTIME = "androidx.paging:paging-runtime:${Versions.Android.Arch.Pagination.PAGING}"

            }

        }

        object Test {

            val CORE = "androidx.test:core:${Versions.Android.Test.CORE}"
            val CORE_KTX = "androidx.test:core-ktx:${Versions.Android.Test.CORE}"
            val J_UNIT = "androidx.test.ext:junit:${Versions.Android.Test.J_UNIT}"
            val RUNNER = "androidx.test:runner:${Versions.Android.Test.CORE}"
            val RULES = "androidx.test:rules:${Versions.Android.Test.CORE}"

            object Espresso {

                val CORE = "androidx.test.espresso:espresso-core:${Versions.Android.Test.ESPRESSO}"
                val CONTRIB = "androidx.test.espresso:espresso-contrib:${Versions.Android.Test.ESPRESSO}"

                @Suppress("unused")
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

    object Di {

        object Koin {

            object Kotlin {

                val CORE = "io.insert-koin:koin-core:${Versions.Di.Koin.KOIN_BETA}"
                val CORE_JVM = "io.insert-koin:koin-core-jvm:${Versions.Di.Koin.KOIN_BETA}"

            }

            @Suppress("MemberNameEqualsClassName")
            object Android {

                val ANDROID = "io.insert-koin:koin-android:${Versions.Di.Koin.KOIN_BETA}"

            }

        }

    }

    object Net {

        object OkHttp {

            val OK_HTTP = "com.squareup.okhttp3:okhttp:${Versions.Net.OkHttp.OK_HTTP_ALPHA}"
            val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.Net.OkHttp.OK_HTTP_ALPHA}"

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

        object Coil {

            val COIL_BASE = "io.coil-kt:coil-base:${Versions.Image.Coil.COIL_RC}"
            val COMPOSE = "io.coil-kt:coil-compose:${Versions.Image.Coil.COIL_RC}"

        }

    }

    object Log {

        val TIMBER = "com.jakewharton.timber:timber:${Versions.Log.TIMBER}"

    }

    // TEST IMPLEMENTATION // ******************************************************************************************

    object Test {

        object JUnit {

            val J_UNIT_4 = "junit:junit:${Versions.Test.JUnit.J_UNIT_4}"

        }

        object Integration {

            val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.Net.OkHttp.OK_HTTP_ALPHA}"

        }

        @Suppress("unused")
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
            val DSL_JVM = "io.mockk:mockk-dsl-jvm:${Versions.Test.Mock.MOCK_K}"

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
