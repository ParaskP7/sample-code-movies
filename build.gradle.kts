buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri(Reps.URL_GRADLE) }
    }
    dependencies {
        classpath(Deps.Plugin.ANDROID)
        classpath(Deps.Plugin.KOTLIN)
        classpath(Deps.Plugin.VERSIONS)
        classpath(Deps.Plugin.DEXCOUNT)
        classpath(Deps.Plugin.DETEKT)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri(Reps.URL_GRADLE) }
    }
}
