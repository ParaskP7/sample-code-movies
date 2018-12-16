plugins {
    id(PluginIds.ANDROID_APPLICATION)
    id(PluginIds.KOTLIN_ANDROID)
    id(PluginIds.KOTLIN_ANDROID_EXTENSIONS)
    id(PluginIds.KOTLIN_KAPT)
    id(PluginIds.VERSIONS)
    id(PluginIds.DEXCOUNT)
    id(PluginIds.DETEKT)
}

apply(Config.GRADLE_ANDROID)
apply(Config.GRADLE_DART)
apply(Config.GRADLE_DEXCOUNT)
apply(Config.GRADLE_LEAK_CANARY)
apply(Config.GRADLE_LINT)
apply(Config.GRADLE_DETEKT)
apply(Config.GRADLE_DEPENDENCY_UPDATES)

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
            proguardFiles(getDefaultProguardFile(Files.TXT_PROGUARD_ANDROID), Files.PRO_PROGUARD_RULES)
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
