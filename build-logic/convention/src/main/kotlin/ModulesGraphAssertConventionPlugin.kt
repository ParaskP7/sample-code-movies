import com.jraska.module.graph.assertion.GraphRulesExtension
import io.petros.movies.Plugins
import io.petros.movies.configureModulesGraphAssert
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ModulesGraphAssertConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Module.GRAPH_ASSERT)
            }
            extensions.configure<GraphRulesExtension> {
                configureModulesGraphAssert(this)
            }
        }
    }
}
