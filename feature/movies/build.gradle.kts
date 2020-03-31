@file:Suppress("InvalidPackageDeclaration", "ForbiddenComment", "FunctionMaxLength")

/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Quality.DETEKT)
    // id(PluginIds.Test.Android.J_UNIT_5) // FIXME: Failed to notify project evaluation listener.
    id(PluginIds.Dependency.VERSIONS)
}

/* DEPENDENCIES */

dependencies {
    projectImplementation()
    implementation()
    testProjectImplementation()
    testImplementation()
    plugins()
}

/* *********************************************************************************************************************** */

/* DEPENDENCIES - PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.projectImplementation() {
    kotlinProjectImplementation()
    androidProjectImplementation()
}

fun DependencyHandlerScope.kotlinProjectImplementation() {
    implementation(project(Project.Implementation.Kotlin.UTILS))
    implementation(project(Project.Implementation.Kotlin.DOMAIN))
}

fun DependencyHandlerScope.androidProjectImplementation() {
    androidProjectImplementationCore()
    androidProjectImplementationFeature()
}

fun DependencyHandlerScope.androidProjectImplementationCore() {
    implementation(project(Project.Implementation.Android.Core.ANDROID_UTILS))
    implementation(project(Project.Implementation.Android.Core.CORE))
}

fun DependencyHandlerScope.androidProjectImplementationFeature() {
    implementation(project(Project.Implementation.Android.Feature.PICKER))
    implementation(project(Project.Implementation.Android.Feature.MOVIE_DETAILS))
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationMaterial()
    implementationAndroidCore()
    implementationAndroidKtx()
    implementationAndroidArch()
    implementationDi()
    implementationLog()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
}

fun DependencyHandlerScope.implementationMaterial() {
    implementation(Deps.Material.MATERIAL)
}

fun DependencyHandlerScope.implementationAndroidCore() {
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.CARD_VIEW)
    implementation(Deps.Android.Core.CONSTRAINT_LAYOUT)
}

fun DependencyHandlerScope.implementationAndroidKtx() {
    implementation(Deps.Android.Ktx.CORE)
}

fun DependencyHandlerScope.implementationAndroidArch() {
    implementation(Deps.Android.Arch.Core.Lifecycle.LIVE_DATA)
    implementation(Deps.Android.Arch.Core.Lifecycle.VIEW_MODEL)
    implementation(Deps.Android.Arch.Core.Lifecycle.VIEW_MODEL_KTX)
}

fun DependencyHandlerScope.implementationDi() {
    implementation(Deps.Di.Koin.Android.ANDROID)
    implementation(Deps.Di.Koin.Android.VIEW_MODEL)
}

fun DependencyHandlerScope.implementationLog() {
    implementation(Deps.Log.TIMBER)
}

/* DEPENDENCIES - TEST PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.testProjectImplementation() {
    kotlinTestProjectImplementation()
    androidTestProjectImplementation()
}

fun DependencyHandlerScope.kotlinTestProjectImplementation() {
    testImplementation(project(Project.TestImplementation.Kotlin.TEST))
}

fun DependencyHandlerScope.androidTestProjectImplementation() {
    testImplementation(project(Project.TestImplementation.Android.ANDROID_TEST))
}

/* DEPENDENCIES - TEST IMPLEMENTATION */

fun DependencyHandlerScope.testImplementation() {
    testImplementationKotlin()
    testImplementationSpek()
    testImplementationJUnit()
    testImplementationTest()
    testImplementationMock()
    testImplementationAndroidArch()
    testImplementationRobolectric()
}

fun DependencyHandlerScope.testImplementationKotlin() {
    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
}

fun DependencyHandlerScope.testImplementationSpek() {
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT_5)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
}

fun DependencyHandlerScope.testImplementationJUnit() {
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_5)
}

fun DependencyHandlerScope.testImplementationTest() {
    testImplementation(Deps.Test.Assert.STRIKT)
}

fun DependencyHandlerScope.testImplementationMock() {
    testImplementation(Deps.Test.Mock.MOCK_K)
}

fun DependencyHandlerScope.testImplementationAndroidArch() {
    testImplementation(Deps.Android.Arch.Test.CORE_TESTING)
}

fun DependencyHandlerScope.testImplementationRobolectric() {
    testImplementation(Deps.Android.Test.ROBOLECTRIC)
}

/* DEPENDENCIES - PLUGINS */

fun DependencyHandlerScope.plugins() {
    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
