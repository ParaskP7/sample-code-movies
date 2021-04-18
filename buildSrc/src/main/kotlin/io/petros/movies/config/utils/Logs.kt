package io.petros.movies.config.utils

fun logPlugin(pluginId: String) {
    println("<<< CONFIGURE WITH $pluginId PLUGIN >>>")
}

fun logBuildTools(buildToolsVersion: String) {
    println("<<< CONFIGURE WITH $buildToolsVersion ANDROID BUILD TOOLS VERSION >>>")
}

fun logModule(isKotlinModule: Boolean) {
    if (isKotlinModule) {
        println("<<< CONFIGURE FOR kotlin MODULE >>>")
    } else {
        println("<<< CONFIGURE FOR android MODULE >>>")
    }
}

fun logVariant(variant: String) {
    println(" << CONFIGURE WITHOUT $variant VARIANT >>")
}

fun logApiKey(apiKey: String) {
    println(" << CONFIGURE WITH $apiKey API KEY>>")
}
