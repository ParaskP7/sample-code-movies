@file:Suppress("InvalidPackageDeclaration", "ForbiddenComment", "FunctionMaxLength")

/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Kotlin.KAPT) // This plugin is required because of Glide.
    id(PluginIds.Quality.DETEKT)
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
}

fun DependencyHandlerScope.kotlinProjectImplementation() {
    implementation(project(Project.Implementation.Kotlin.DOMAIN))
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationAndroid()
    implementationImage()
    implementationLog()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.Core.KOTLIN)
}

fun DependencyHandlerScope.implementationAndroid() {
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.RECYCLER_VIEW)
}

fun DependencyHandlerScope.implementationImage() {
    implementation(Deps.Image.Glide.GLIDE)
    kapt(Deps.Image.Glide.GLIDE_COMPILER)
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
    testImplementationJUnit()
    testImplementationTest()
    testImplementationMock()
    testImplementationRobolectric()
}

fun DependencyHandlerScope.testImplementationJUnit() {
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
}

fun DependencyHandlerScope.testImplementationTest() {
    testImplementation(Deps.Test.Assert.STRIKT)
}

fun DependencyHandlerScope.testImplementationMock() {
    testImplementation(Deps.Test.Mock.MOCK_K)
}

fun DependencyHandlerScope.testImplementationRobolectric() {
    testImplementation(Deps.Android.Test.ROBOLECTRIC)
}

/* DEPENDENCIES - PLUGINS */

fun DependencyHandlerScope.plugins() {
    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
