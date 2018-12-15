object Deps {

    /* PLUGINS */

    val pluginAndroid = "com.android.tools.build:gradle:${Versions.Plugin.ANDROID}"
    val pluginKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugin.KOTLIN}"
    val pluginVersions = "com.github.ben-manes:gradle-versions-plugin:${Versions.Plugin.VERSIONS}"
    val pluginDexcount = "com.getkeepsafe.dexcount:dexcount-gradle-plugin:${Versions.Plugin.DEXCOUNT}"
    val pluginDetekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.PLUGIN_DETEKT}"
    val pluginDetektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.PLUGIN_DETEKT}"

    /* LEAK CANARY */

    val leakCanaryDebug = "com.squareup.leakcanary:leakcanary-android:${Versions.LeakCanary.LEAK_CANARY}"
    val leakCanaryRelease = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.LeakCanary.LEAK_CANARY}"

    // IMPLEMENTATION // ****************************************************************************************************

    /* KOTLIN */

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Plugin.KOTLIN}"

    /* MATERIAL */

    val material = "com.google.android.material:material:${Versions.Material.MATERIAL}"

    /* ANDROID */

    val androidAppCompat = "androidx.appcompat:appcompat:${Versions.Android.APP_COMPAT}"
    val androidRecyclerView = "androidx.recyclerview:recyclerview:${Versions.Android.RECYCLER_VIEW}"
    val androidCardView = "androidx.cardview:cardview:${Versions.Android.CARD_VIEW}"
    val androidConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Android.CONSTRAINT_LAYOUT}"

    /* ANDROID KTX */

    val androidKtxCore = "androidx.core:core-ktx:${Versions.AndroidKtx.KTX_CORE}"

    /* ANDROID ARCH */

    val androidArchLifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidArch.LIFECYCLE_EXTENSIONS}"

    /* DI */

    val diDagger = "com.google.dagger:dagger:${Versions.Di.DAGGER}"
    val diDaggerCompiler = "com.google.dagger:dagger-compiler:${Versions.Di.DAGGER}"
    val diDaggerAndroid = "com.google.dagger:dagger-android-support:${Versions.Di.DAGGER}"
    val diDaggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.Di.DAGGER}"

    /* RX */

    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.Rx.RX_JAVA}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.RX_ANDROID}"

    /* NET */

    val netGson = "com.google.code.gson:gson:${Versions.Net.GSON}"
    val netOkHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.Net.OK_HTTP}"

    /* REST */

    val restRetrofit = "com.squareup.retrofit2:retrofit:${Versions.Rest.RETROFIT}"
    val restRetrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.Rest.RETROFIT}"
    val restRetrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.Rest.RETROFIT}"

    /* IMAGE */

    val imageGlide = "com.github.bumptech.glide:glide:${Versions.Image.GLIDE}"
    val imageGlideCompiler = "com.github.bumptech.glide:compiler:${Versions.Image.GLIDE}"

    /* DART */

    val extrasDart = "com.f2prateek.dart:dart:${Versions.Extras.DART}"
    val extrasartProcessor = "com.f2prateek.dart:dart-processor:${Versions.Extras.DART}"
    val extrasHenson = "com.f2prateek.dart:henson:${Versions.Extras.DART}"
    val extrasHensonProcessor = "com.f2prateek.dart:henson-processor:${Versions.Extras.DART}"

    /* UTIL */

    val utilMonthYearPicker = "com.whiteelephant:monthandyearpicker:${Versions.Util.MONTH_YEAR_PICKER}"

    /* LOGGING */

    val logTimber = "com.jakewharton.timber:timber:${Versions.Log.TIMBER}"

    // TEST IMPLEMENTATION // ***********************************************************************************************

    /* TEST */

    val testJUnit = "junit:junit:${Versions.Test.J_UNIT}"
    val testAssertJ = "org.assertj:assertj-core:${Versions.Test.ASSERT_J}"

    /* MOCK */

    val mockMockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.Mock.MOCKITO_KOTLIN}"

    /* ANDROID ARCH TEST */

    val androidArchTestCoreTesting = "androidx.arch.core:core-testing:${Versions.AndroidArchTest.CORE_TESTING}"

    /* ROBOLECTRIC */

    val robolectric = "org.robolectric:robolectric:${Versions.Robolectric.ROBOLECTRIC}"

    // ANDROID TEST IMPLEMENTATION // ***************************************************************************************

    /* ANDROID TEST */

    val androidTestCore = "androidx.test:core:${Versions.AndroidTest.TEST_CORE}"
    val androidTestRunner = "androidx.test.ext:junit:${Versions.AndroidTest.TEST_J_UNIT}"
    val androidTestEspresso = "androidx.test.espresso:espresso-core:${Versions.AndroidTest.TEST_ESPRESSO}"

}
