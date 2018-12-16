import org.gradle.api.tasks.testing.logging.TestLogEvent

object Logs {

    const val EXCEPTION_FORMAT = "full"

    val events = arrayOf(
        "passed",
        "skipped",
        "failed",
        "standardOut",
        "standardError"
    )

    val eventsKts = arrayOf(
        TestLogEvent.PASSED,
        TestLogEvent.SKIPPED,
        TestLogEvent.FAILED,
        TestLogEvent.STANDARD_OUT,
        TestLogEvent.STANDARD_ERROR
    )

}
