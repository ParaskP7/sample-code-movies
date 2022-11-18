package io.petros.movies

import com.autonomousapps.DependencyAnalysisExtension
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider

internal fun configureDependencyAnalysis(
    dependencyAnalysisExtension: DependencyAnalysisExtension,
) {
    dependencyAnalysisExtension.apply {
        issues()
        abi()
    }
}

/* ISSUES */

fun DependencyAnalysisExtension.issues() {
    issues {
        all {
            onUnusedDependencies {
                severity(DependencyAnalysis.Issue.Severity.FAIL)
            }
            onUsedTransitiveDependencies {
                severity(DependencyAnalysis.Issue.Severity.FAIL)
            }
            onIncorrectConfiguration {
                severity(DependencyAnalysis.Issue.Severity.FAIL)
            }
            onCompileOnly {
                severity(DependencyAnalysis.Issue.Severity.FAIL)
            }
            onUnusedAnnotationProcessors {
                severity(DependencyAnalysis.Issue.Severity.FAIL)
            }
            onRedundantPlugins {
                severity(DependencyAnalysis.Issue.Severity.FAIL)
            }
        }
    }
}

/* ABI */

fun DependencyAnalysisExtension.abi() {
    abi {
        exclusions {
            ignoreInternalPackages()
            ignoreGeneratedCode()
        }
    }
}

/* IDENTIFIER */

fun Provider<MinimalExternalModuleDependency>.identifier(): String {
    val groupNameVersion = get().toString().split(Utils.COLON)
    return groupNameVersion[0] + Utils.COLON + groupNameVersion[1]
}

/* CONFIG */

object DependencyAnalysis {

    object Issue {

        object Severity {

            const val FAIL = "fail"

        }

    }

}
