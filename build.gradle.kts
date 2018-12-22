import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.detekt

import com.github.benmanes.gradle.versions.VersionsPlugin

buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri(Reps.Url.GRADLE) }
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
        maven { url = uri(Reps.Url.GRADLE) }
    }
}

subprojects {
    plugins.withType(DetektPlugin::class) {
        detekt {
            toolVersion = Versions.Plugin.DETEKT
            config = files(Config.Detekt.CONFIG_FILE_PATH)
            filters = Config.Detekt.FILTERS
            disableDefaultRuleSets = false
            parallel = true
        }
    }
    plugins.withType(VersionsPlugin::class) {
        apply(Config.Gradle.DEPENDENCY_UPDATES)
    }
}
