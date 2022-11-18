package io.petros.movies

import com.jraska.module.graph.assertion.GraphRulesExtension

internal fun configureModulesGraphAssert(
    graphRulesExtension: GraphRulesExtension,
) {
    graphRulesExtension.apply {
        maxHeight = ModuleGraphAssert.MAX_HEIGHT
        allowed = ModuleGraphAssert.allowed
        restricted = ModuleGraphAssert.restricted
        configurations = ModuleGraphAssert.configurations
        assertOnAnyBuild = true
    }
}

/* CONFIG */

object ModuleGraphAssert {

    const val MAX_HEIGHT = 5

    val allowed = arrayOf(
        ":app\\S* -> :feature:\\S*",
        ":app\\S* -> :core\\S*",
        ":app\\S* -> :utils\\S*",
        ":app\\S* -> :android-utils\\S*",
        ":app\\S* -> :data\\S*",
        ":app\\S* -> :domain\\S*",
        ":feature:\\S* -> :lib\\S*",
        ":feature:\\S* -> :core\\S*",
        ":feature:\\S* -> :domain\\S*",
        ":feature:\\S* -> :utils\\S*",
        ":feature:\\S* -> :android-utils\\S*",
        ":lib:\\S* -> :core\\S*",
        ":core\\S* -> :android-utils\\S*",
        ":data\\S* -> :network\\S*",
        ":data\\S* -> :database\\S*",
        ":data\\S* -> :datastore\\S*",
        ":data\\S* -> :domain\\S*",
        ":network\\S* -> :domain\\S*",
        ":network\\S* -> :utils\\S*",
        ":database\\S* -> :domain\\S*",
        ":database\\S* -> :utils\\S*",
        ":domain\\S* -> :utils\\S*",
        ":android-utils\\S* -> :utils\\S*",
        ":test\\S* -> :domain\\S*",
    )
    val restricted = arrayOf(
        ":feature-[a-z]* -X> :forbidden-to-depend-on",
    )
    val configurations = setOf(
        "api",
        "implementation",
    )

}
