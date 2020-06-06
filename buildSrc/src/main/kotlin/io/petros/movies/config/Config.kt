package io.petros.movies.config

import io.petros.movies.config.dirs.Files
import io.petros.movies.config.dirs.Folders

object Config {

    private const val QUALITY_DIRECTORY = "${Folders.Config.CONFIG}/${Folders.Config.Subfolder.QUALITY}"

    object Gradle {

        const val THEMOVIEDB_API_KEY_CONST = "THEMOVIEDB_API_KEY"

    }

    object Lint {

        const val CONFIG_FILE_PATH = "$QUALITY_DIRECTORY/${Files.Xml.LINT}"

        val disabledIssues = arrayOf(
            "MissingRegistered", // Because of UAST
            "UnusedIds", // Because of UAST
            "ContentDescription",
            "SelectableText",
            "InvalidPackage"
        )

    }

    object Detekt {

        const val CONFIG_FILE_PATH = "$QUALITY_DIRECTORY/${Files.Yml.DETEKT}"

    }

    object Jacoco {

        object Robolectric {

            val excludes = listOf("jdk.internal.*")
        }

        private const val REPORT_DIRECTORY_PATH = "build/reports/jacoco"
        private const val REPORT_HTML_DIRECTORY = "jacocoHtml"
        private const val REPORT_XML_FILE = "jacoco.xml"
        private const val REPORT_CSV_FILE = "jacoco.csv"

        const val GROUP = "Reporting"
        const val DESCRIPTION = "Generate Jacoco code coverage reports."

        const val REPORT_HTML_DIRECTORY_PATH = "$REPORT_DIRECTORY_PATH/$REPORT_HTML_DIRECTORY"
        const val REPORT_XML_FILE_PATH = "$REPORT_DIRECTORY_PATH/$REPORT_XML_FILE"
        const val REPORT_CSV_FILE_PATH = "$REPORT_DIRECTORY_PATH/$REPORT_CSV_FILE"

        object ClassDirectories {

            fun dirPair(isKotlinModule: Boolean): Pair<String, Any> {
                val kotlinDir = "build/classes/kotlin/main"
                val androidDir = "build/tmp/kotlin-classes/debug"
                return "dir" to if (isKotlinModule) kotlinDir else androidDir
            }

            fun excludesPair(isKotlinModule: Boolean): Pair<String, Any> {
                val kotlinExcludes = listOf(
                    "**/*Module*.*"
                )
                val androidExcludes = listOf(
                    "**/*Activity*.*",
                    "**/*Fragment*.*",
                    "**/*Mvi*.*",
                    "**/*Stateful*.*",
                    "**/*Module*.*",
                    "**/*Binding*.*",
                    "**/*Glide*.*"
                )
                return "excludes" to if (isKotlinModule) kotlinExcludes else androidExcludes
            }

        }

        object ExecutionData {

            fun dirPair(): Pair<String, Any> {
                val dir = "build"
                return "dir" to dir
            }

            fun includePair(isKotlinModule: Boolean): Pair<String, Any> {
                val kotlinExecutionData = listOf(
                    "jacoco/test.exec"
                )
                val androidExecutionData = listOf(
                    "jacoco/testDebugUnitTest.exec"
                )
                return "include" to if (isKotlinModule) kotlinExecutionData else androidExecutionData
            }

        }

    }

    object Coverage {

        const val REPORT_FILE_PATH = Jacoco.REPORT_XML_FILE_PATH
        const val CONFIG_FILE_PATH = "coverage.properties"

    }

    object Dependency {

        object Versions {

            const val GRADLE_RELEASE_CHANNEL = "release-candidate"
            const val OUTPUT_FORMATTER = "json,xml,txt"
            const val OUTPUT_DIR = "build/dependencyUpdates"
            const val REPORT_FILE_NAME = "report"

            const val REGEX = "^[0-9,.v-]+(-r)?$"
            val stableKeyword = listOf("RELEASE", "FINAL", "GA")
            val nonStableKeyword = listOf("M1")

        }

        object Analysis {

            object Issue {

                object Severity {

                    const val FAIL = "fail"
                    const val WARN = "warn"

                }

            }

        }

    }

    object Module {

        @Suppress("MagicNumber", "ForbiddenComment")
        object GraphAssert {

            const val MAX_HEIGHT = 5 // '4' is the default.

            val moduleLayers = arrayOf(":feature:\\S*", ":core\\S*") // TODO: ":lib\\S*"
            val moduleLayersExclude = arrayOf(":feature:movies -> :feature:picker")
            val restricted = arrayOf(":feature-[a-z]* -X> :forbidden-to-depend-on")
            val configurations = setOf("api", "implementation")

        }

    }

}
