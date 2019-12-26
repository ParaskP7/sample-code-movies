/* PLUGINS */

plugins {
    id(PluginIds.Kotlin.KOTLIN)
    id(PluginIds.Kotlin.KAPT)
    // TODO: Re-add Detekt plugin.
}

/* DEPENDENCIES */

dependencies {
    projectImplementation()
    implementation()
}

/* *********************************************************************************************************************** */

/* DEPENDENCIES - PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.projectImplementation() {
    implementation(project(Project.Implementation.DOMAIN))
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.Core.KOTLIN)
}
