buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri(Reps.URL_GRADLE) }
    }
    dependencies {
        classpath(Deps.pluginAndroid)
        classpath(Deps.pluginKotlin)
        classpath(Deps.pluginVersions)
        classpath(Deps.pluginDexcount)
        classpath(Deps.pluginDetekt)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri(Reps.URL_GRADLE) }
    }
}
