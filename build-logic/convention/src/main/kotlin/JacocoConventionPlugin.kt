import io.petros.movies.Plugins
import io.petros.movies.configureJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension

class JacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Test.JACOCO)
            }
            extensions.configure<JacocoPluginExtension> {
                configureJacoco(this)
            }
        }
    }
}
