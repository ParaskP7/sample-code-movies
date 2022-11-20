import com.google.devtools.ksp.gradle.KspExtension
import io.petros.movies.Plugins
import io.petros.movies.configureKsp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KspConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Kotlin.KSP)
            }
            extensions.configure<KspExtension> {
                configureKsp(this)
            }
        }
    }
}
