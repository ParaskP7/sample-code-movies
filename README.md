# Sample Code: Movies

This repository contains sample code. 

Its purpose being, to quickly demonstrate Android, Kotlin and software development in general. More so and amongst others, 
the main focus of this project is:
- Setup and Gradle configuration, 
- Gradle modules,
- Clean architecture,
- Clean code,
- Best practices,
- Testing and 
- All those other must know goodies.

Below is a list of goodies that are being showcased:

1. Architectural Pattern
    1. [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) ```(By employing 
    clean architecture, you can design applications with very low coupling and independent of technical implementation 
    details, such as databases and frameworks. That way, the application becomes easy to maintain and flexible to change. 
    It also becomes intrinsically testable.)```
    2. [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) ```(Model View ViewModel)```
    3. [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) ```(Architecture Components 
    ViewModel Class)```
    4. [LivaData](https://developer.android.com/topic/libraries/architecture/livedata) ```(Architecture Components 
    LiveData Class)```
2. Libraries
    1. [Koin](https://github.com/InsertKoinIO/koin) ```(A pragmatic lightweight dependency injection framework for Kotlin)```
    2. [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines.html) ```(Coroutines simplify asynchronous
    programming by putting the complications into libraries. The logic of the program can be expressed sequentially in a
    coroutine, and the underlying library will figure out the asynchrony for us)```
    3. [Retrofit](https://github.com/square/retrofit) ```(Type-safe HTTP client for Android and Java by Square, Inc.)```
    4. [GSON](https://github.com/google/gson) ```(A Java serialization/deserialization library to convert Java Objects into 
    JSON and back)```
    5. [Glide](https://github.com/bumptech/glide) ```(An image loading and caching library for Android focused on smooth 
    scrolling)```
    6. [Timber](https://github.com/JakeWharton/timber) ```(A logger with a small, extensible API which provides utility on
    top of Android's normal Log class)```
3. Android Support
    1. [AndroidX](https://developer.android.com/topic/libraries/support-library/androidx-overview) ```(A new package
    structure to make it clearer which packages are bundled with the Android operating system, and which are packaged with
    your app's APK)```
    2. [Android KTX](https://developer.android.com/kotlin/ktx) ```(Android KTX is a set of Kotlin extensions that is part of
    the Android Jetpack family)```
    3. [Constraint Layout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout) ```(A
    ConstraintLayout is a ViewGroup which allows you to position and size widgets in a flexible way)```
    4. [Card View](https://developer.android.com/reference/android/support/v7/widget/CardView.html) ```(A FrameLayout with a
    rounded corner background and shadow)```
    5. [Recycler View](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html) ```(A flexible
    view for providing a limited window into a large data set)```
    6. [Shared Element Transition](https://developer.android.com/training/material/animations.html#Transitions) ```(Activity
    transitions in material design apps provide visual connections between different states through motion and 
    transformations between common elements)```
4. Code Quality
    1. [Android Lint](https://developer.android.com/studio/write/lint.html) ```(The lint tool checks your Android project 
    source files for potential bugs and optimization improvements for correctness, security, performance, usability, 
    accessibility, and internationalization)```
    2. [Detekt](https://github.com/arturbosch/detekt) ```(Static code analysis for Kotlin)```
5. Tests
    1. [JUnit4](https://junit.org/junit4) ```(A programmer-oriented testing framework for Java)```
    2. [JUnit5](https://junit.org/junit5) ```(JUnit 5 is the next generation of JUnit)```
    3. [Spek](https://spekframework.org) ```(A specification framework for Kotlin)```
    4. [Strikt](https://strikt.io) ```(Strikt is an assertion library for Kotlin intended for use with a test runner such as 
    JUnit or Spek)```
    5. [MockK](https://mockk.io) ```(MockK is a mocking library for Kotlin)```
    6. [Robolectric](https://github.com/robolectric/robolectric) ```(Android Unit Testing Framework)```
6. Debug
    1. [LeakCanary](https://github.com/square/leakcanary) ```(A memory leak detection library for Android and Java)```
    1. [Strict Mode](https://developer.android.com/reference/android/os/StrictMode) ```(StrictMode is a developer tool which 
    detects things you might be doing by accident and brings them to your attention so you can fix them)```
7. Build
    1. [Gradle Kotlin DSL](https://github.com/gradle/kotlin-dsl) ```(Kotlin language support for Gradle build scripts)```
    2. [Gradle Versions](https://github.com/ben-manes/gradle-versions-plugin) ```(Gradle plugin to discover dependency
    updates)```

# Screenshots

![alt tag](https://github.com/ParaskP7/sample-code-movies/blob/master/demo.png)

# Usage

The first thing that you need to do in order to be able to build this project is to obtain your personal keys and place them 
under the below config directory:
```
config/keys/
```

With the current configuration the build will be completed successful. However, in order for you to play with the app you 
will need to obtain your personal api key from "The Movie Database" and replace the existing mocked api key with yours 
within the below property file:
```
themoviedb_api.properties
```

For more information on how to obtain your personal TMDB api key visit  
[The Movie Database API (v3)](https://developers.themoviedb.org/3/getting-started/introduction).

After this, you might want to ignore this change and make git forget all about that. In order to do so, run the below git 
command:
```
git update-index --assume-unchanged config/keys/themoviedb_api.properties
```

# Build Variant

This project is setup in a way that can be configured to filter out on demand specific build variants by just specifying the 
'ignored_variants' property, which is located within the 'local.properties' file. If no configuration is specified, then the 
default configuration will be applied, which is all build types and flavors.

To apply this configuration first locate the 'local.properties' file, and if it doesn't exist already, create it. Then, add 
the 'ignored_variants' property and assign that to a value. This value can be a single variant value, such as the 'release' 
built type or a comma separated list of variant values, such as 'debug,release,etc'. Any variant that goes in there will be 
then filtered out by the build.
```
This is mainly done in order to speed up build times and complexity during development, thus making developers happier since 
in most cases developers usually only care about the'debug' build type (and a single build flavor, if that is even available).
```

# Building The Project

All set, use the below command to build the project in order to install it on an Android device for demonstration:
```
gradlew clean build -x check
```

Or faster yet and targeting a specific build type (in our case the debug build type):
```
gradlew clean assembleDebug
```

Open an emulator or connect a physical device to experiment with the sample app, use the below command, which first 
uninstalls and then installs the sample app:
```
gradlew uninstallDebug | gradlew installDebug
```

Or faster yet, target a specific device (in our case an emulator):
```
adb -s emulator-5554 uninstall io.petros.movies | 
adb -s emulator-5554 install app\build\outputs\apk\debug\app-debug.apk
```

Use this command in order to run the static code analysis for the project:
```
gradlew check -x test
```

Or if you want to be more specific, run the below commands to run the code quality tool or your choice (in isolation):
```
gradlew detekt
gradlew lintDebug
```

Run the project unit tests using this command (this includes Robolectric):
```
gradlew test
```

Or if you want to be more specific, run the below commands to run the tests or your choice (per module):
```
gradlew <kotlin_module>:test
gradlew <android_module>:testDebugUnitTest
```

Run the project instrumentation tests using this command (Espresso):
```
gradlew app:connectedDebugAndroidTest
```

Run the project coverage reports using this command:
```
gradlew coverage
```

Or if you want to just run the Jacoco reports without comparing the results with each other, use the below command:
```
gradlew jacoco
```

Use this command in order to check dependency updates for the project:
```
gradlew dependencyUpdates
```

# Future

Below is a list of all those ```I REALLY WANNA DO``` future architecture and library enhancements:
01. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Update Gradle to the Latest Version. 
    For more info, see [Gradle Release Notes](https://docs.gradle.org/current/release-notes.html)
02. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Update Android Studio to the Latest Canary Version. 
    For more info, see [Android Studio Release Updates](https://androidstudio.googleblog.com)
03. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace current to the new AndroidX Package Structure. 
    For more info, see [AndroidX](https://developer.android.com/topic/libraries/support-library/androidx-overview) 
    ```(A new package structure to make it clearer which packages are bundled with the Android operating system, and which are packaged with your app's APK)```
04. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace Manual Android Extensions with Android KTX. 
    For more info, see [Android KTX](https://developer.android.com/kotlin/ktx) 
    ```(Android KTX is a set of Kotlin extensions that is part of the Android Jetpack family)```
05. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace Gradle Groovy with Kotlin DSL. 
    For more info, see [Gradle Kotlin DSL](https://github.com/gradle/kotlin-dsl)
    ``` (Kotlin language support for Gradle build scripts)```
06. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace RxJava with Coroutines.
    For more info, see [Coroutines](https://kotlinlang.org/docs/reference/coroutines.html) 
    ```(Coroutines simplify asynchronous programming by putting the complications into libraries. The logic of the program can be expressed sequentially in a coroutine, and the underlying library will figure out the asynchrony for us)```
07. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace Dagger with Koin.
    For more info, see [Koin](https://github.com/InsertKoinIO/koin) 
    ```(A pragmatic lightweight dependency injection framework for Kotlin)```
08. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace AssertJ with Strikt.
    For more info see [Strikt](https://strikt.io) 
    ```(Strikt is an assertion library for Kotlin intended for use with a test runner such as JUnit or Spek)```
09. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace Mockito Kotlin with MockK.
    For more info see [MockK](https://mockk.io) 
    ```(MockK is a mocking library for Kotlin)```
10. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Upgrade JUnit4 to Spek Framework and JUnit5. 
    For more info, see [Spek](https://spekframework.org) 
    ```(A specification framework for Kotlin)``` 
    and [JUnit5](https://junit.org/junit5) 
    ```(JUnit 5 is the next generation of JUnit)```
11. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Support Android 10 (Target SDK 29). 
    For more info, see [Android 10](https://developer.android.com/about/versions/10)
    ```(Build app experiences with dark theme and gesture navigation. Support new protections for user privacy and security)```
12. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Re-add Detekt Plugin.
    For more info, see [Detekt](https://arturbosch.github.io/detekt) 
    ```(Static code analysis for Kotlin)```
13. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Find new Versions Plugin.
    For example, see [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin)
    ```(Gradle plugin to discover dependency updates)```
14. **![#ffff00](https://placehold.it/15/ff0000/000000?text=+) `PAUSED`** Find new Dexcount Plugin.
    For example, see [Dexcount Gradle Plugin](https://github.com/KeepSafe/dexcount-gradle-plugin)
    ```(A Gradle plugin to report the number of method references in your APK on every build)```
15. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Migrate to View Binding (From Kotlin Android Extensions Plugin). 
    For more info, see [View Binding](https://developer.android.com/topic/libraries/view-binding) 
    ```(View binding is a feature that allows you to more easily write code that interacts with views. Once view binding is enabled in a module, it generates a binding class for each XML layout file present in that module. An instance of a binding class contains direct references to all views that have an ID in the corresponding layout)```
16. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Modularize the App Horizontally by Features.
    To get an understanding of Modularization and how to it applies to an Android project, start with this Article [Modularization](https://medium.com/google-developer-experts/modularizing-android-applications-9e2d18f244a0)
    ```(To take advantage of new distribution features (Instant apps, app bundles) from Google, or even just create a clear separation of concerns to make our project easier to work with— modularizing our applications can help us to achieve all of these things)```
17. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Add Result for Error Handling.
    To get an understanding of the power of types for errors, start with this Article [Designing Errors with Kotlin](https://arturdryomov.dev/posts/designing-errors-with-kotlin/)
    ```(Exceptions! Developers adore exceptions. It is so easy to throw an error and forget about consequences. Is it a good idea though? Should Kotlin follow the same path? Fortunately enough there are many good languages we can learn from)```
18. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Add Code Coverage Reports for Tests with Jacoco.
    For more info see [Jacoco](https://github.com/jacoco/jacoco)
    ```(Java Code Coverage Library)```
19. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`**  Add Integration Tests with MockWebServer (See `MIT` comment which stands for `Missing Integration Tests`).
    For more info, see [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
    ```(A scriptable web server for testing HTTP clients)``` 
20. Add missing Unit Tests (See `MUT` comment which stands for `Missing Unit Tests`).
    For more info, see [JUnit4](https://junit.org/junit4/)
    ```(JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks)``` 
21. Add missing Robolectric Tests (See `MRT` comment which stands for `Missing Robolectric Tests`).
    For more info, see [Robolectric](http://robolectric.org)
    ```(Robolectric is a framework that brings fast and reliable unit tests to Android)``` 
22. Add missing Espresso Tests (See `MET` comment which stands for `Missing Espresso Tests`).
    For more info, see [Espresso](https://developer.android.com/training/testing/espresso)
    ```(Use Espresso to write concise, beautiful, and reliable Android UI tests)``` 
23. Add Support for Dark Theme.
    For more info see [Dark Theme](https://developer.android.com/guide/topics/ui/look-and-feel/darktheme)
    ```(Dark theme is available in Android 10 (API level 29) and higher)```
24. Enhance MVVM with MVI.
    To get an understanding of MVI and how it applies to MVVM (or MVP), start with this Article [MVI](http://hannesdorfmann.com/android/model-view-intent)
    ```(Model-View_Intent, is an architecture enhancment that tries to solve the state problem, which most complex application have, especially when the screen complexity grows)```
25. Replace Manual Navigation with the Navigation Architecture Component.
    For more info, see [Navigation Architecture Component](https://developer.android.com/topic/libraries/architecture/navigation)
    ```(The Navigation Architecture Component simplifies the implementation of navigation in an Android app)```
26. Add Offline Support with Room. 
    For more info, see [Room](https://developer.android.com/topic/libraries/architecture/room) 
    ```(The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite)```
27. Replace Manual Pagination with the Paging Architecture Component. 
    For more info, see [Paging Architecture Component](https://developer.android.com/topic/libraries/architecture/paging) 
    ```(The Paging Library makes it easier for you to load data gradually and gracefully within your app's RecyclerView)```
28. Add Chucker for HTTP Inspection.
    For more info see [Chucker](https://github.com/ChuckerTeam/chucker)
    ```🔎 An HTTP inspector for Android & OkHTTP (like Charles but on device) - More Chucker than Chuck```
29. Add Bitrise CI.
    For more info see [Bitrise](https://www.bitrise.io)
    ```(Continuous Integration and Continuous Delivery for mobile apps)```
30. Add Support for R8 (ProGuard).
    For more info see [ProGuard and R8: a comparison of optimizers](https://www.guardsquare.com/en/blog/proguard-and-r8)
    ```(ProGuard and R8 have three important functions, Shrinking or tree shaking: removes unused classes, fields and methods from the application, Code optimization: makes the code smaller and more efficient at the instruction level, Name obfuscation: renames the remaining classes, fields and methods with short meaningless names. At this point, it mostly reduces the size of the code)```
31. Sing the App.
    For more info see [App Singing](https://developer.android.com/studio/publish/app-signing)
    ```(Android requires that all APKs be digitally signed with a certificate before they can be installed. And you need to sign your Android App Bundle before you can upload it to the Play Console.)```
32. Create an Automated Release Process using Gradle Play Publisher Plugin.
    For more info see [Gradle Play Publisher](https://github.com/Triple-T/gradle-play-publisher)
    ```(Gradle plugin to upload your App Bundle or APK and other app details to the Google Play Store)```
33. Convert APK Upload Format to App Bundles.
    For more info see [App Bundles](https://developer.android.com/guide/app-bundle)
    ```(An Android App Bundle is a new upload format that includes all your app’s compiled code and resources, but defers APK generation and signing to Google Play)```
34. Add Jetpack Compose.
    For more info see [Jetpack Compose](https://developer.android.com/jetpack/compose)
    ```(Jetpack Compose simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs)```
35. Add Jetpack Benchmark.
    For more info see [Jetpack Benchmark](https://developer.android.com/studio/profile/benchmark)
    ```(The Jetpack Benchmark library allows you to quickly benchmark your Kotlin-based or Java-based code from within Android Studio. The library handles warmup, measures your code performance, and outputs benchmarking results to the Android Studio console)```
36. Integrate Maps into the App. 
    For more info see [Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk/intro) 
    ```(With the Maps SDK for Android, you can add maps based on Google Maps data to your application)```
37. Add Background Support with Work Manager.
    For more info see [Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager)
    ```(The WorkManager API makes it easy to specify deferrable, asynchronous tasks and when they should run)```
38. Add Support for Material Design 2.0.
    For more info see [Material Design 2.0](https://material.io)
    ```(Make beautiful products, faster. Material is a design system – backed by open-source code – that helps teams build digital experiences)```
39. Enhance ConstraintLayout with MotionLayout. 
    For more info see [MotionLayout](https://developer.android.com/reference/android/support/constraint/motion/MotionLayout) 
    ```(A MotionLayout is a ConstraintLayout which allows you to animate layouts between various states)```
40. Add Support for App Shortcuts.
    For more info see [App Shortcuts](https://developer.android.com/guide/topics/ui/shortcuts) 
    ```(Define shortcuts to perform specific actions in your app. These shortcuts can be displayed in a supported launcher and help your users quickly start common or recommended tasks within your app)```
41. Add Runtime Permissions with Permissions Dispatcher Library.
    For more info see [Permissions Dispatcher](https://github.com/permissions-dispatcher/PermissionsDispatcher) 
    ```(Simple annotation-based API to handle runtime permissions.)```
42. Add Settings Screen. 
    For more info see [Settings](https://developer.android.com/guide/topics/ui/settings) 
    ```(Settings allow users to change the functionality and behavior of an application)```
43. Add Support for Firebase Crashlytics.
    For more info see [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics)
    ```(Get clear, actionable insight into app issues with this powerful crash reporting solution for Android and iOS)```
44. Add Support for Firebase Performance Monitoring.
    For more info see [Firebase Performance Monitoring](https://firebase.google.com/docs/perf-mon)
    ```(Gain insight into your app's performance issues)```
45. Add Support for Firebase Remote Config. 
    For more info see [Firebase Remote Config](https://firebase.google.com/docs/remote-config) 
    ```(Change the behavior and appearance of your app without publishing an app update, at no cost, for unlimited daily active users.)```
46. Add Support for Firebase Push Notifications. 
    For more info see [Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging) 
    ```(Firebase Cloud Messaging (FCM) is a cross-platform messaging solution that lets you reliably deliver messages at no cost)```
47. Create a Continue Integration Environment using GitLab.
    For more info see [GitLab Continuous Integration & Delivery](https://about.gitlab.com/product/continuous-integration)
    ```(GitLab has integrated CI/CD pipelines to build, test, deploy, and monitor your code)```
48. Enable Deep Links and Android App Links.
    For more info see [Android App Links](https://developer.android.com/training/app-links)
    ```(Set up Android App Links to take users to a link's specific content directly in your app, bypassing the app-selection dialog, also known as the disambiguation dialog)```
49. Add Support for Finger Print Login.
    For more info see [Fingerprint Authentication](https://developer.android.com/about/versions/marshmallow/android-6.0#fingerprint-authentication) 
    ```(New APIs to let you authenticate users by using their fingerprint scans on supported devices)```
50. Add Support for Instant Apps.
    For more info see [Google Play Instant](https://developer.android.com/topic/google-play-instant/overview)
    ```(Google Play Instant enables native apps and games to launch on devices running Android 5.0 (API level 21) without being installed)```
51. Add Support for Slices.
    For more info see [Slices](https://developer.android.com/guide/slices)
    ```(Slices are UI templates that can display rich, dynamic, and interactive content from your app from within the Google Search app and  later in other places like the Google Assistant)```
52. Add AdMob as a Source of App Monetization.
    For more info see [AdMob](https://admob.google.com/home)
    ```(AdMob makes earning revenue easy with in-app ads, actionable insights, and powerful, easy-to-use tools that grow your app business)```
53. Add Google Play Billing as a Source of App Monetization.
    For more info see [Google Play Billing Library](https://developer.android.com/google/play/billing/billing_overview) 
    ```(Google Play Billing is a service that lets you sell digital content from inside an Android app, or in-app)```
54. Add Support for Machine Learning.
    For more info see [ML Kit](https://developers.google.com/ml-kit)
    ```(ML Kit beta brings Google’s machine learning expertise to mobile developers in a powerful and easy-to-use package)```
55. Make the App compliant with Android's Accessibility Features and Services. 
    For more info see [Accessibility](https://developer.android.com/guide/topics/ui/accessibility) 
    ```(Accessibility is an important part of any app. Whether you're developing a new app or improving an existing one, consider the accessibility of your app's components)```
56. Convert Imperative to Functional Programming.
    For more info see [Arrow](https://arrow-kt.io)
    ```(Functional companion to Kotlin's Standard Library)```
57. Add Kotlin Native Support to Build the iOS equivalent App. 
    For more info see [Kotlin Native](https://kotlinlang.org/docs/reference/native-overview.html) 
    ```(Kotlin/Native is a technology for compiling Kotlin code to native binaries, which can run without a virtual machine)```
58. Add Kotlin Multiplatform Support to Share Code between the Android and iOS App.
    For more info see [Kotlin Multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) 
    ```(Kotlin Multiplatform brings the invaluable benefit of reuse for code and expertise, saving the effort for tasks more challenging than implementing everything twice or multiple times)```
59. Last but not least, Convert the whole thing to Flutter. 
    For more info see [Flutter](https://flutter.io)
    ```(JUST KIDDING 😛 ...OR AM I!)```


**THANK YOU**
