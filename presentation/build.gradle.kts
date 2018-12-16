plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.github.ben-manes.versions")
    id("com.getkeepsafe.dexcount")
    id("io.gitlab.arturbosch.detekt")
}

apply("../config/gradle/android/android.gradle")
apply("../config/gradle/android/dart.gradle")
apply("../config/gradle/android/dexcount.gradle")
apply("../config/gradle/android/leakcanary.gradle")
apply("../config/gradle/quality/lint.gradle")
apply("../config/gradle/quality/detekt.gradle")
apply("../config/gradle/dependencies/dependency_updates.gradle")

android {
    defaultConfig {
        applicationId = App.APPLICATION_ID
        versionCode = App.VERSION_CODE
        versionName = App.VERSION_NAME
        testInstrumentationRunner = Android.TEST_INSTRUMENTATION_RUNNER
    }
    buildTypes {
        named("debug") {
            applicationIdSuffix = App.DEBUG_APPLICATION_ID_SUFFIX
            versionNameSuffix = App.DEBUG_VERSION_NAME_SUFFIX
            isDebuggable = true
        }
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(Files.PROGUARD_ANDROID), Files.PROGUARD_RULES)
        }
    }
}

kapt {
    useBuildCache = true
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    debugImplementation(Deps.leakCanaryDebug)
    releaseImplementation(Deps.leakCanaryRelease)

    implementation(Deps.kotlin)
    implementation(Deps.material)
    implementation(Deps.androidAppCompat)
    implementation(Deps.androidRecyclerView)
    implementation(Deps.androidCardView)
    implementation(Deps.androidConstraintLayout)
    implementation(Deps.androidKtxCore)
    implementation(Deps.androidArchLifecycleExtensions)
    implementation(Deps.diDagger)
    kapt(Deps.diDaggerCompiler)
    implementation(Deps.diDaggerAndroid)
    kapt(Deps.diDaggerAndroidProcessor)
    implementation(Deps.rxJava)
    implementation(Deps.rxAndroid)
    implementation(Deps.netGson)
    implementation(Deps.netOkHttpLogging)
    implementation(Deps.restRetrofit)
    implementation(Deps.imageGlide)
    kapt(Deps.imageGlideCompiler)
    implementation(Deps.extrasDart)
    kapt(Deps.extrasartProcessor)
    implementation(Deps.extrasHenson)
    kapt(Deps.extrasHensonProcessor)
    implementation(Deps.utilMonthYearPicker)
    implementation(Deps.logTimber)

    testImplementation(project(":test"))

    testImplementation(Deps.testJUnit)
    testImplementation(Deps.testAssertJ)
    testImplementation(Deps.mockMockitoKotlin, {
        exclude(ExcludedDeps.groupJetbrainsKotlin, ExcludedDeps.moduleKotlinReflect)
    })
    testImplementation(Deps.androidArchTestCoreTesting)
    testImplementation(Deps.androidTestCore)
    testImplementation(Deps.robolectric)

    androidTestImplementation(Deps.androidTestCore)
    androidTestImplementation(Deps.androidTestRunner)
    androidTestImplementation(Deps.androidTestEspresso)

    detektPlugins(Deps.pluginDetektFormatting)
}
