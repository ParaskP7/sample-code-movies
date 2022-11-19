import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import io.petros.movies.Android
import io.petros.movies.Plugins
import io.petros.movies.configureIgnoredVariants
import io.petros.movies.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Android.APPLICATION)
                apply(Plugins.Id.Kotlin.ANDROID)
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                targetSdk()
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configureIgnoredVariants(this)
            }
        }
    }
}

fun ApplicationExtension.targetSdk() {
    defaultConfig.targetSdk = Android.Sdk.TARGET
}
