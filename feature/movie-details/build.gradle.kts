@file:Suppress("InvalidPackageDeclaration", "ForbiddenComment")

/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Kotlin.Android.ANDROID)
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
    implementation(project(Project.Implementation.DOMAIN))
    implementation(project(Project.Implementation.ANDROID_UTILS))
    implementation(project(Project.Implementation.CORE))
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationMaterial()
    implementationAndroid()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.Core.KOTLIN)
}

fun DependencyHandlerScope.implementationMaterial() {
    implementation(Deps.Material.MATERIAL)
}

fun DependencyHandlerScope.implementationAndroid() {
    implementation(Deps.Android.Core.APP_COMPAT)
    implementation(Deps.Android.Core.CONSTRAINT_LAYOUT)
}

/* DEPENDENCIES - TEST PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.testProjectImplementation() {
    testImplementation(project(Project.TestImplementation.TEST))
    testImplementation(project(Project.TestImplementation.ANDROID_TEST))
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
