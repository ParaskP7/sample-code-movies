package io.petros.movies.config.tests

import io.petros.movies.config.utils.Utils.COLON
import io.petros.movies.config.utils.Utils.COMMA
import io.petros.movies.config.utils.Utils.DASH
import io.petros.movies.config.utils.Utils.EMPTY
import io.petros.movies.config.utils.Utils.LEFT_PARENTHESIS
import io.petros.movies.config.utils.Utils.NEW_LINE
import io.petros.movies.config.utils.Utils.PIPE
import io.petros.movies.config.utils.Utils.RIGHT_PARENTHESIS
import io.petros.movies.config.utils.Utils.SPACE
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.logging.TestLogEvent

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
        val output = "$RESULTS$COLON$SPACE${result.resultType}$SPACE$LEFT_PARENTHESIS" +
                "${result.testCount}$SPACE$TESTS$COMMA$SPACE" +
                "${result.successfulTestCount}$SPACE$PASSED$COMMA$SPACE" +
                "${result.failedTestCount}$SPACE$FAILED$COMMA$SPACE" +
                "${result.skippedTestCount}$SPACE$SKIPPED$RIGHT_PARENTHESIS"
        val startItem = "$PIPE$SPACE$SPACE"
        val endItem = "$SPACE$SPACE$PIPE"
        val repeatLength = startItem.length + output.length + endItem.length
        var dashes = EMPTY
        repeat(repeatLength) { dashes += DASH }
        return "$NEW_LINE$dashes$NEW_LINE$startItem$output$endItem$NEW_LINE$dashes"
    }

}
