import com.autonomousapps.DependencyAnalysisExtension
import io.petros.movies.Plugins
import io.petros.movies.configureDependencyAnalysis
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class DependencyAnalysisConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Dependency.ANALYSIS)
            }
            extensions.configure<DependencyAnalysisExtension> {
                configureDependencyAnalysis(this)
            }
        }
    }
}
