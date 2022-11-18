import com.osacky.doctor.DoctorExtension
import io.petros.movies.Plugins
import io.petros.movies.configureGradleDoctor
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class GradleDoctorConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(Plugins.Id.Gradle.DOCTOR)
            }
            extensions.configure<DoctorExtension> {
                configureGradleDoctor(this)
            }
        }
    }
}
