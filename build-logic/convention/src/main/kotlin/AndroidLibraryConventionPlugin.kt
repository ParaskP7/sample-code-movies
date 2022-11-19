import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import io.petros.movies.Plugins
import io.petros.movies.configureIgnoredVariants
import io.petros.movies.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Android.LIBRARY)
                apply(Plugins.Id.Kotlin.ANDROID)
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                configureIgnoredVariants(this)
            }
        }
    }
}
