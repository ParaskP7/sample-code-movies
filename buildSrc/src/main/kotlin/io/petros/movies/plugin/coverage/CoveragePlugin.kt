package io.petros.movies.plugin.coverage

import org.gradle.api.Plugin
import org.gradle.api.Project

class CoveragePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create(EXTENSION_NAME, CoverageExtension::class.java)
        target.tasks.create(TASK_NAME, CoverageTask::class.java)
    }

    companion object {

        private const val EXTENSION_NAME = "coverage"
        private const val TASK_NAME = "coverage"

    }

}
