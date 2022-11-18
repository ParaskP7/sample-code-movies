import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.petros.movies.Plugins
import io.petros.movies.configureDetekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Quality.DETEKT)
            }
            extensions.configure<DetektExtension> {
                configureDetekt(this)
            }
        }
    }
}
