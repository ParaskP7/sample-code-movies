package io.petros.movies

import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.logging.TestLogEvent

@Suppress("SpreadOperator")
fun Test.testLogging() {
    testLogging {
        events(*Logs.events)
        setExceptionFormat(Logs.EXCEPTION_FORMAT)
        debug {
            events(*Logs.debugEvents)
            setExceptionFormat(Logs.DEBUG_EXCEPTION_FORMAT)
        }
        showExceptions = true
        showCauses = true
        showStackTraces = true
        info.events = debug.events
        info.exceptionFormat = debug.exceptionFormat
        addTestListener(
            object : TestListener {
                override fun beforeSuite(desc: TestDescriptor) = Unit
                override fun beforeTest(desc: TestDescriptor) = Unit
                override fun afterTest(desc: TestDescriptor, result: TestResult) = Unit
                override fun afterSuite(desc: TestDescriptor, result: TestResult) {
                    if (desc.parent != null) println(Logs.testConsoleOutput(result))
                }
            }
        )
    }
}

/* CONFIG */

object Logs {

    const val EXCEPTION_FORMAT = "full"
    const val DEBUG_EXCEPTION_FORMAT = "full"

    private const val RESULTS = "Results"
    private const val TESTS = "tests"
    private const val PASSED = "passed"
    private const val FAILED = "failed"
    private const val SKIPPED = "skipped"

    val events = arrayOf(
        TestLogEvent.SKIPPED,
        TestLogEvent.FAILED,
    )

    val debugEvents = arrayOf(
        TestLogEvent.STARTED,
        TestLogEvent.PASSED,
        TestLogEvent.SKIPPED,
        TestLogEvent.FAILED,
        TestLogEvent.STANDARD_OUT,
        TestLogEvent.STANDARD_ERROR,
    )

    /**
     * An example console output is the below.
     * ---------------------------------------------------------------------
     * |  Results: SUCCESS (1255 tests, 1251 passed, 2 failed, 2 skipped)  |
     * ---------------------------------------------------------------------
     */
    fun testConsoleOutput(result: TestResult): String {
        val output = "$RESULTS${Utils.COLON}${Utils.SPACE}${result.resultType}${Utils.SPACE}${Utils.LEFT_PARENTHESIS}" +
                "${result.testCount}${Utils.SPACE}$TESTS${Utils.COMMA}${Utils.SPACE}" +
                "${result.successfulTestCount}${Utils.SPACE}$PASSED${Utils.COMMA}${Utils.SPACE}" +
                "${result.failedTestCount}${Utils.SPACE}$FAILED${Utils.COMMA}${Utils.SPACE}" +
                "${result.skippedTestCount}${Utils.SPACE}$SKIPPED${Utils.RIGHT_PARENTHESIS}"
        val startItem = "${Utils.PIPE}${Utils.SPACE}${Utils.SPACE}"
        val endItem = "${Utils.SPACE}${Utils.SPACE}${Utils.PIPE}"
        val repeatLength = startItem.length + output.length + endItem.length
        var dashes = Utils.EMPTY
        repeat(repeatLength) { dashes += Utils.DASH }
        return "${Utils.NEW_LINE}$dashes${Utils.NEW_LINE}$startItem$output$endItem${Utils.NEW_LINE}$dashes"
    }

}
