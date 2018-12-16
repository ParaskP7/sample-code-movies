import org.gradle.api.tasks.testing.logging.TestLogEvent

object Logs {

    /* TEST */

    val listOfTestLoggingEvents = arrayOf(
        "passed",
        "skipped",
        "failed",
        "standardOut",
        "standardError"
    )
    val testLogEvents = arrayOf(
        TestLogEvent.PASSED,
        TestLogEvent.SKIPPED,
        TestLogEvent.FAILED,
        TestLogEvent.STANDARD_OUT,
        TestLogEvent.STANDARD_ERROR
    )
    const val TEST_LOGGING_EXCEPTION_FORMAT = "full"

}
