package io.petros.movies.config

import io.petros.movies.config.dirs.Files
import io.petros.movies.config.dirs.Folders

object Config {

    private const val QUALITY_DIRECTORY = "${Folders.Config.CONFIG}/${Folders.Config.Subfolder.QUALITY}"

    object Gradle {

        const val THEMOVIEDB_API_KEY_CONST = "THEMOVIEDB_API_KEY"

    }

    object Kotlin {

        object Options {

            val freeCompilerArgs = listOf(
                "-opt-in=kotlin.RequiresOptIn",
            )

        }

    }

    object Lint {

        const val CONFIG_FILE_PATH = "$QUALITY_DIRECTORY/${Files.Xml.LINT}"

        val disabledIssues = arrayOf(
            "UnusedIds", // Because of UAST
            "InvalidPackage",
            "DialogFragmentCallbacksDetector", // From Android Gradle Plugin version 7.4.0-alpha02 and onwards
            "UnknownIssueId", // From Android Gradle Plugin version 7.4.0-alpha02 and onwards
        )

        val disabledDatabaseIssues = arrayOf(
            "RestrictedApi", // From Android Gradle Plugin version 8.0.0-alpha03 and onwards
            "SyntheticAccessor", // From Android Gradle Plugin version 8.0.0-alpha03 and onwards
            "UnknownNullness", // From Android Gradle Plugin version 8.0.0-alpha03 and onwards
        )

    }

    object Detekt {

        const val CONFIG_FILE_PATH = "$QUALITY_DIRECTORY/${Files.Yml.DETEKT}"

    }

    object Jacoco {

        object Robolectric {

            val excludes = listOf(
                "jdk.internal.*",
            )

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
                    "**/*Module*.*",
                )
                val androidExcludes = listOf(
                    "**/*Activity*.*",
                    "**/*Fragment*.*",
                    "**/*Mvi*.*",
                    "**/*Module*.*",
                    "**/*Binding*.*",
                    "**/*Glide*.*",
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
                    "jacoco/test.exec",
                )
                val androidExecutionData = listOf(
                    "jacoco/testDebugUnitTest.exec",
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

        @Suppress("MemberVisibilityCanBePrivate")
        object Versions {

            const val GRADLE_RELEASE_CHANNEL = "release-candidate"
            const val OUTPUT_FORMATTER = "plain,json,xml,html"
            const val OUTPUT_DIR = "build/reports/dependency-updates"
            const val REPORT_FILE_NAME = "advice"

            const val REGEX = "^[0-9,.v-]+(-r)?$"
            val devKeyword = listOf(
                "dev",
            )
            val alphaKeyword = listOf(
                "alpha",
            )
            val milestoneKeyword = listOf(
                "M1",
                "M2",
            )
            val stableKeyword = listOf(
                "RELEASE",
                "FINAL",
                "GA",
            )
            val nonStableKeyword = listOf<String>() + milestoneKeyword

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

        object GraphAssert {

            const val MAX_HEIGHT = 5

            val allowed = arrayOf(
                ":app\\S* -> :feature:\\S*",
                ":app\\S* -> :core\\S*",
                ":app\\S* -> :android-utils\\S*",
                ":app\\S* -> :data\\S*",
                ":app\\S* -> :domain\\S*",
                ":feature:\\S* -> :lib\\S*",
                ":feature:\\S* -> :core\\S*",
                ":feature:\\S* -> :domain\\S*",
                ":feature:\\S* -> :utils\\S*",
                ":feature:\\S* -> :android-utils\\S*",
                ":lib:\\S* -> :core\\S*",
                ":core\\S* -> :android-utils\\S*",
                ":data\\S* -> :network\\S*",
                ":data\\S* -> :database\\S*",
                ":data\\S* -> :datastore\\S*",
                ":data\\S* -> :domain\\S*",
                ":network\\S* -> :domain\\S*",
                ":network\\S* -> :utils\\S*",
                ":database\\S* -> :domain\\S*",
                ":database\\S* -> :utils\\S*",
                ":domain\\S* -> :utils\\S*",
                ":android-utils\\S* -> :utils\\S*",
                ":test\\S* -> :domain\\S*",
            )
            val restricted = arrayOf(
                ":feature-[a-z]* -X> :forbidden-to-depend-on",
            )
            val configurations = setOf(
                "api",
                "implementation",
            )

        }

    }

}
