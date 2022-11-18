import com.android.build.gradle.LibraryExtension
import io.petros.movies.Config.LocalProperties
import io.petros.movies.Plugins
import io.petros.movies.Utils
import io.petros.movies.configureKotlinAndroid
import io.petros.movies.findLocalProperty
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
                val ignoredVariants = findLocalProperty(LocalProperties.Gradle.IGNORED_VARIANTS)?.split(Utils.COMMA)
                ignoredVariants?.forEach {
                    @Suppress("DEPRECATION", "ForbiddenComment") // TODO: Think about 'ignoredVariants!
                    variantFilter {
                        if (name == it) ignore = true
                    }
                }
            }
        }
    }
}
