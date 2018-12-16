plugins {
    id(PluginIds.Android.APPLICATION)
    id(PluginIds.Android.DEXCOUNT)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Kotlin.Android.EXTENSIONS)
    id(PluginIds.Kotlin.KAPT)
    id(PluginIds.Dependency.VERSIONS)
    id(PluginIds.Quality.DETEKT)
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
        named(BuildTypes.DEBUG) {
            applicationIdSuffix = App.DEBUG_APPLICATION_ID_SUFFIX
            versionNameSuffix = App.DEBUG_VERSION_NAME_SUFFIX
            isDebuggable = true
        }
        named(BuildTypes.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(Files.TXT_PROGUARD_ANDROID), Files.PRO_PROGUARD_RULES)
        }
    }
}

kapt {
    useBuildCache = true
}

dependencies {
    implementation(project(Project.Implementation.DOMAIN))
    implementation(project(Project.Implementation.DATA))

    debugImplementation(Deps.LeakCanary.DEBUG)
    releaseImplementation(Deps.LeakCanary.RELEASE)

    implementation(Deps.Kotlin.KOTLIN)
    implementation(Deps.Material.MATERIAL)
    implementation(Deps.Android.APP_COMPAT)
    implementation(Deps.Android.RECYCLER_VIEW)
    implementation(Deps.Android.CARD_VIEW)
    implementation(Deps.Android.CONSTRAINT_LAYOUT)
    implementation(Deps.Android.Ktx.CORE)
    implementation(Deps.Android.Arch.LIFECYCLE_EXTENSIONS)
    implementation(Deps.Di.DAGGER)
    kapt(Deps.Di.DAGGER_COMPILER)
    implementation(Deps.Di.DAGGER_ANDROID)
    kapt(Deps.Di.DAGGER_ANDROID_PROCESSOR)
    implementation(Deps.Rx.RX_JAVA)
    implementation(Deps.Rx.RX_ANDROID)
    implementation(Deps.Net.GSON)
    implementation(Deps.Net.OK_HTTP_LOGGING)
    implementation(Deps.Net.Rest.RETROFIT)
    implementation(Deps.Image.GLIDE)
    kapt(Deps.Image.GLIDE_COMPILER)
    implementation(Deps.Extras.DART)
    kapt(Deps.Extras.DART_PROCESSOR)
    implementation(Deps.Extras.HENSON)
    kapt(Deps.Extras.HENSON_PROCESSOR)
    implementation(Deps.Util.MONTH_YEAR_PICKER)
    implementation(Deps.Log.TIMBER)

    testImplementation(project(Project.TestImplementation.TEST))

    testImplementation(Deps.Test.J_UNIT)
    testImplementation(Deps.Test.ASSERT_J)
    testImplementation(Deps.Mock.MOCKITO_KOTLIN, {
        exclude(ExcludedDeps.Group.Jetbrains.KOTLIN, ExcludedDeps.Module.Kotlin.REFLECT)
    })
    testImplementation(Deps.Android.Arch.Test.CORE_TESTING)
    testImplementation(Deps.Android.Test.CORE)
    testImplementation(Deps.Robolectric.ROBOLECTRIC)

    androidTestImplementation(Deps.Android.Test.CORE)
    androidTestImplementation(Deps.Android.Test.J_UNIT)
    androidTestImplementation(Deps.Android.Test.ESPRESSO)

    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
