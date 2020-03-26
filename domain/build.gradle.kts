@file:Suppress("InvalidPackageDeclaration")

/* PLUGINS */

plugins {
    id(PluginIds.Kotlin.KOTLIN)
    id(PluginIds.Quality.DETEKT)
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
    implementation(project(Project.Implementation.UTILS))
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationDi()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
}

fun DependencyHandlerScope.implementationDi() {
    implementation(Deps.Di.Koin.Kotlin.CORE)
}

/* DEPENDENCIES - TEST PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.testProjectImplementation() {
    testImplementation(project(Project.TestImplementation.TEST))
}

/* DEPENDENCIES - TEST IMPLEMENTATION */

fun DependencyHandlerScope.testImplementation() {
    testImplementationKotlin()
    testImplementationSpek()
    testImplementationJUnit()
    testImplementationTest()
    testImplementationMock()
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

/* DEPENDENCIES - PLUGINS */

fun DependencyHandlerScope.plugins() {
    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
