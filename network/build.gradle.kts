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
    implementation(project(Project.Implementation.DOMAIN))
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationDi()
    implementationNet()
    implementationRetrofit()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
}

fun DependencyHandlerScope.implementationDi() {
    implementation(Deps.Di.Koin.Kotlin.CORE)
}

fun DependencyHandlerScope.implementationNet() {
    implementation(Deps.Net.OkHttp.OK_HTTP_LOGGING)
}

fun DependencyHandlerScope.implementationRetrofit() {
    implementation(Deps.Net.Rest.RETROFIT)
    implementation(Deps.Net.Rest.RETROFIT_GSON)
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
    testImplementation(Deps.Test.Spek.J_UNIT)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
}

fun DependencyHandlerScope.testImplementationJUnit() {
    testImplementation(Deps.Test.JUnit.J_UNIT)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_VINTAGE)
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
