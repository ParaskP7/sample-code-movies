import io.petros.movies.Plugins
import io.petros.movies.configureJava
import io.petros.movies.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin

class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Kotlin.KOTLIN)
            }
            extensions.configure<JavaPluginExtension> {
                configureJava(this)
            }
            plugins.withType(KotlinBasePlugin::class) {
                configureKotlin(this)
            }
        }
    }
}
