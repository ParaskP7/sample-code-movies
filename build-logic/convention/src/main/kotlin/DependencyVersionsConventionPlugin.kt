import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.petros.movies.Plugins
import io.petros.movies.configureDependencyVersions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class DependencyVersionsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Dependency.VERSIONS)
            }
            tasks.withType<DependencyUpdatesTask> {
                configureDependencyVersions(this)
            }
        }
    }
}
