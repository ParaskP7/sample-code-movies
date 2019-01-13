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
    1. [JUnit](https://github.com/junit-team/junit4) ```(A programmer-oriented testing framework for Java)```
    2. [Strikt](https://strikt.io) ```(Strikt is an assertion library for Kotlin intended for use with a test runner such as 
    JUnit or Spek)```
    3. [Mockito Kotlin](https://github.com/nhaarman/mockito-kotlin) ```(Using Mockito with Kotlin)```
    4. [Robolectric](https://github.com/robolectric/robolectric) ```(Android Unit Testing Framework)```
6. Debug
    1. [LeakCanary](https://github.com/square/leakcanary) ```(A memory leak detection library for Android and Java)```
    1. [Strict Mode](https://developer.android.com/reference/android/os/StrictMode) ```(StrictMode is a developer tool which 
    detects things you might be doing by accident and brings them to your attention so you can fix them)```
7. Build
    1. [Gradle Kotlin DSL](https://github.com/gradle/kotlin-dsl) ```(Kotlin language support for Gradle build scripts)```
    2. [Gradle Versions](https://github.com/ben-manes/gradle-versions-plugin) ```(Gradle plugin to discover dependency
    updates)```
    3. [Dexcount Gradle](https://github.com/KeepSafe/dexcount-gradle-plugin) ```(A Gradle plugin to report the number of
    method references in your APK on every build)```

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
adb -s emulator-5554 install presentation\build\outputs\apk\debug\presentation-debug.apk
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
gradlew domain:test
gradlew data:testDebugUnitTest
gradlew presentation:testDebugUnitTest
```

# Future

Below is a list of all those ```I WISH I HAD MORE TIME TO DO``` future technical enhancements:
1. Espresso tests (preferable with the Robot pattern). See `MET` comment which stands for `Missing Espresso Tests`
2. Integration tests (preferable with MockWebServer). See `MIT` comment which stands for `Missing Integration Tests`
3. Add missing Robolectric tests. See `MRT` comment which stands for `Missing Robolectric Tests`
4. Add missing Unit tests. See `MUT` comment which stands for `Missing Unit Tests`
5. Small, medium and large screen considerations.
6. UI changes during screen rotations (edge cases).
7. Other lifecycle related edge case events.

Furthermore, below is a wish list of all those ```I REALLY WANNA DO, AND WILL DO AT SOME POINT``` future architecture 
and library enhancements:
01. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Update Gradle to the Latest Version. 
    For more info, see [Gradle Release Notes](https://docs.gradle.org/current/release-notes.html)
02. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Update Android Studio to the Latest Canary Version. 
    For more info, see [Android Studio Release Updates](https://androidstudio.googleblog.com)
03. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace current to the new AndroidX Package Structure. 
    For more info, see [AndroidX](https://developer.android.com/topic/libraries/support-library/androidx-overview) ```(A new 
    package structure to make it clearer which packages are bundled with the Android operating system, and which are packaged 
    with your app's APK)```
04. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace Manual Android Extensions with Android KTX. 
    For more info, see [Android KTX](https://developer.android.com/kotlin/ktx) ```(Android KTX is a set of Kotlin extensions 
    that is part of the Android Jetpack family)```
05. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace Gradle Groovy with Kotlin DSL. 
    For more info, see [Gradle Kotlin DSL](https://github.com/gradle/kotlin-dsl) ``` (Kotlin language support for Gradle 
    build scripts)```
06. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace RxJava with Coroutines.
    For more info, see [Coroutines](https://kotlinlang.org/docs/reference/coroutines.html) ```(Coroutines simplify 
    asynchronous programming by putting the complications into libraries. The logic of the program can be expressed 
    sequentially in a coroutine, and the underlying library will figure out the asynchrony for us)```
07. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace Dagger with Koin.
    For more info, see [Koin](https://github.com/InsertKoinIO/koin) ```(A pragmatic lightweight dependency injection
    framework for Kotlin)```
08. **![#2cb42c](https://placehold.it/15/2cb42c/000000?text=+) `DONE`** Replace AssertJ with Strikt.
    For more info see [Strikt](https://strikt.io/) ```(Strikt is an assertion library for Kotlin intended for use with a 
    test runner such as JUnit or Spek)```
09. **![#ffff00](https://placehold.it/15/ffff00/000000?text=+) `IN PROGRESS`** Upgrade JUnit4 to SPEK Framework and JUnit5. 
    For more info, see [SPEK](https://spekframework.org/) ```(A specification framework for Kotlin)``` 
    and [JUnit5](https://junit.org/junit5/) ```(JUnit 5 is the next generation of JUnit)```
10. Add Code Coverage Reports for Tests with Jacoco. For more info see [Jacoco](https://github.com/jacoco/jacoco) 
    ```(Java Code Coverage Library)```
11. Modularize the App Horizontally by Features. To get an Understanding of Modularization and how to Apply it to any Android 
    project, Start With this Article 
    [Modularization](https://medium.com/google-developer-experts/modularizing-android-applications-9e2d18f244a0) ```(To take 
    advantage of new distribution features (Instant apps, app bundles) from Google, or even just create a clear separation of 
    concerns to make our project easier to work with— modularizing our applications can help us to achieve all of these 
    things)```
12. Add KtLint as an extra Static Code Analysis Tool. For more info see [KtLint](https://github.com/shyiko/ktlint) ```(An 
    anti-bikeshedding Kotlin linter with built-in formatter )```
13. Enhance MVVM with MVI. To get an Understanding of MVI and how it Applies to MVVM (or MVP), Start With this Article
    [MVI](http://hannesdorfmann.com/android/model-view-intent) ```(Model-View_Intent, is an architecture enhancment that 
    tries to solve the state problem, which most complex application have, especially when the screen complexity grows)```
14. Add Offline Support with Room. For more info, see [Room](https://developer.android.com/topic/libraries/architecture/room) ```
    (The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while 
    harnessing the full power of SQLite)```
15. Integrate Maps into the App. For more info see 
    [Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk/intro) ```(With the Maps SDK for 
    Android, you can add maps based on Google Maps data to your application)```
16. Replace Manual Navigation with the Navigation Architecture Component. For more info, see 
    [Navigation Architecture Component](https://developer.android.com/topic/libraries/architecture/navigation/) ```(The 
    Navigation Architecture Component simplifies the implementation of navigation in an Android app)```
17. Replace Manual Pagination with the Paging Architecture Component. For more info, see 
    [Paging Architecture Component](https://developer.android.com/topic/libraries/architecture/paging/) ```(The Paging 
    Library makes it easier for you to load data gradually and gracefully within your app's RecyclerView)```
18. Add Background Support with Work Manager. For more info see 
    [Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager) ```(The WorkManager API makes it 
    easy to specify deferrable, asynchronous tasks and when they should run)```
19. Add Support for Material Design 2.0. For more info see [Material Design 2.0](https://material.io) ```(Make beautiful 
    products, faster. Material is a design system – backed by open-source code – that helps teams build digital 
    experiences)```
20. Enhance ConstraintLayout with MotionLayout. For more info see 
    [MotionLayout](https://developer.android.com/reference/android/support/constraint/motion/MotionLayout) ```(A 
    MotionLayout is a ConstraintLayout which allows you to animate layouts between various states)```
21. Add Support for App Shortcuts. For more info see 
    [App Shortcuts](https://developer.android.com/guide/topics/ui/shortcuts) ```(Define shortcuts to perform specific actions 
    in your app. These shortcuts can be displayed in a supported launcher and help your users quickly start common or 
    recommended tasks within your app)```
22. Add Runtime Permissions with Permissions Dispatcher Library. For more info see 
    [Permissions Dispatcher](https://github.com/permissions-dispatcher/PermissionsDispatcher) ```(Simple annotation-based API 
    to handle runtime permissions.)```
23. Add Settings Screen. For more info see [Settings](https://developer.android.com/guide/topics/ui/settings) ```(Settings 
    allow users to change the functionality and behavior of an application)```
24. Add Support for Firebase Crashlytics. For more info see 
    [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) ```(Get clear, actionable insight into app issues 
    with this powerful crash reporting solution for Android and iOS)```
25. Add Support for Firebase Performance Monitoring. For more info see 
    [Firebase Performance Monitoring](https://firebase.google.com/docs/perf-mon) ```(Gain insight into your app's performance 
    issues)```
26. Add Support for Firebase Remote Config. For more info see 
    [Firebase Remote Config](https://firebase.google.com/docs/remote-config) ```(Change the behavior and appearance of your 
    app without publishing an app update, at no cost, for unlimited daily active users.)```
27. Add Support for Firebase Push Notifications. For more info see 
    [Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging) ```(Firebase Cloud Messaging (FCM) is a 
    cross-platform messaging solution that lets you reliably deliver messages at no cost)```
28. Create a Continue Integration Environment using GitLab. For more info see 
    [GitLab Continuous Integration & Delivery](https://about.gitlab.com/product/continuous-integration/) ```(GitLab has 
    integrated CI/CD pipelines to build, test, deploy, and monitor your code)```
29. Add Support for R8 (ProGuard). For more info see 
    [ProGuard and R8: a comparison of optimizers](https://www.guardsquare.com/en/blog/proguard-and-r8) ```(ProGuard and R8 
    have three important functions, Shrinking or tree shaking: removes unused classes, fields and methods from the 
    application, Code optimization: makes the code smaller and more efficient at the instruction level, Name obfuscation: 
    renames the remaining classes, fields and methods with short meaningless names. At this point, it mostly reduces the size 
    of the code)```
30. Sing the App. For more info see [App Singing](https://developer.android.com/studio/publish/app-signing) ```(Android 
    requires that all APKs be digitally signed with a certificate before they can be installed. And you need to sign your 
    Android App Bundle before you can upload it to the Play Console.)```
31. Create an Automated Release Process using Gradle Play Publisher Plugin. For more info see 
    [Gradle Play Publisher](https://github.com/Triple-T/gradle-play-publisher) ```(Gradle plugin to upload your App Bundle or 
    APK and other app details to the Google Play Store)```
32. Convert APK Upload Format to App Bundles. For more info see 
    [App Bundles](https://developer.android.com/guide/app-bundle) ```(An Android App Bundle is a new upload format that 
    includes all your app’s compiled code and resources, but defers APK generation and signing to Google Play)```
33. Enable Deep Links and Android App Links. For more info see 
    [Android App Links](https://developer.android.com/training/app-links) ```(Set up Android App Links to take users to a 
    link's specific content directly in your app, bypassing the app-selection dialog, also known as the disambiguation 
    dialog)```
34. Add Support for Finger Print Login. For more info see 
    [Fingerprint Authentication](https://developer.android.com/about/versions/marshmallow/android-6.0#fingerprint-authentication) 
    ```(New APIs to let you authenticate users by using their fingerprint scans on supported devices)```
35. Add Support for Instant Apps. For more info see 
    [Google Play Instant](https://developer.android.com/topic/google-play-instant/overview) ```(Google Play Instant enables 
    native apps and games to launch on devices running Android 5.0 (API level 21) without being installed)```
36. Add Support for Slices. For more info see [Slices](https://developer.android.com/guide/slices) ```(Slices are UI 
    templates that can display rich, dynamic, and interactive content from your app from within the Google Search app and 
    later in other places like the Google Assistant)```
37. Add AdMob as a Source of App Monetization. For more info see [AdMob](https://admob.google.com/home) ```(AdMob makes 
    earning revenue easy with in-app ads, actionable insights, and powerful, easy-to-use tools that grow your app 
    business)```
38. Add Google Play Billing as a Source of App Monetization. For more info see 
    [Google Play Billing Library](https://developer.android.com/google/play/billing/billing_overview) ```(Google Play Billing 
    is a service that lets you sell digital content from inside an Android app, or in-app)```
39. Add Support for Machine Learning. For more info see [ML Kit](https://developers.google.com/ml-kit) ```(ML Kit beta brings 
    Google’s machine learning expertise to mobile developers in a powerful and easy-to-use package)```
40. Make the App compliant with Android's Accessibility Features and Services. For more info see 
    [Accessibility](https://developer.android.com/guide/topics/ui/accessibility) ```(Accessibility is an important part of 
    any app. Whether you're developing a new app or improving an existing one, consider the accessibility of your app's 
    components)```
41. Convert Imperative to Functional Programming. For more info see [Arrow](https://arrow-kt.io) ```(Functional companion to 
    Kotlin's Standard Library)```
42. Add Kotlin Native Support to Build the iOS equivalent App. For more info see 
    [Kotlin Native](https://kotlinlang.org/docs/reference/native-overview.html) ```(Kotlin/Native is a technology for 
    compiling Kotlin code to native binaries, which can run without a virtual machine)```
43. Add Kotlin Multiplatform Support to Share Code between the Android and iOS App. For more info see 
    [Kotlin Multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) ```(Kotlin Multiplatform brings the 
    invaluable benefit of reuse for code and expertise, saving the effort for tasks more challenging than implementing 
    everything twice or multiple times)```
44. Last but not least, Convert the whole thing to Flutter. For more info see [Flutter](https://flutter.io) 
    ```(JUST KIDDING 😛 ...OR AM I!)```


**THANK YOU**
