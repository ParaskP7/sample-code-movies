package io.petros.movies.plugin.coverage

import groovy.util.Node
import groovy.util.XmlParser
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import java.io.File
import java.io.FileInputStream
import java.util.*

open class CoverageTask : DefaultTask() {

    companion object {

        private const val XML_FEATURE_LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd"
        private const val XML_FEATURE_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl"

        private const val HUNDRED_PERCENT = 100
        private const val JACOCO_XML_NODE_COUNTER = "counter"
        private const val JACOCO_XML_NODE_COUNTER_ATTRIBUTE_TYPE = "type"
        private const val JACOCO_XML_NODE_COUNTER_ATTRIBUTE_COVERED = "covered"
        private const val JACOCO_XML_NODE_COUNTER_ATTRIBUTE_MISSED = "missed"
        private val JACOCO_XML_NODE_COUNTER_ATTRIBUTE_TYPE_VALUES = listOf(
            "INSTRUCTION",
            "LINE",
            "COMPLEXITY",
            "METHOD",
            "CLASS"
        )

        private const val COVERAGE_TYPE_SENTENCE_LENGTH = 28
        private const val SPACE = " "
        private const val DASH = "-"
        private const val PERCENT = "%"

        private const val CODE_COVERAGE_SUCCESS_RESULT_LINE = "---------------- Code Coverage Success ----------------"
        private const val CODE_COVERAGE_FAILURE_RESULT_INE = "---------------- Code Coverage Failure ----------------"
        private const val CODE_COVERAGE_END_RESULT_LINE = "-------------------------------------------------------"

    }

    private val xmlParser = XmlParser().apply {
        setFeature(XML_FEATURE_LOAD_EXTERNAL_DTD, false)
        setFeature(XML_FEATURE_DISALLOW_DOCTYPE_DECL, false)
    }

    @TaskAction
    fun coverage() {
        val report = project.extensions.getByType<CoverageExtension>().report
        val coverage = project.extensions.getByType<CoverageExtension>().config

        if (report != null && coverage != null) {
            val actualCoverageMetrics = actualCoverageMetrics(project.file(report))
            val expectedCoverageMetrics = expectedCoverageMetrics(project.file(coverage))
            printMetricsResults(metricsResults(actualCoverageMetrics, expectedCoverageMetrics))
        } else {
            throw GradleException("Code coverage failure (Make sure to provide both a 'report' and 'coverage' path)")
        }
    }

    @Suppress("UNCHECKED_CAST", "DefaultLocale")
    private fun actualCoverageMetrics(report: File): Map<String, Double> {
        val node = xmlParser.parse(report)
        val counters = node[JACOCO_XML_NODE_COUNTER] as List<Node>
        val metrics = linkedMapOf<String, Double>()
        JACOCO_XML_NODE_COUNTER_ATTRIBUTE_TYPE_VALUES.forEach { metrics[it.toLowerCase()] = coverageMetric(counters, it) }
        return metrics
    }

    private fun coverageMetric(counters: List<Node>, type: String): Double {
        for (counter in counters) {
            if (counter.attribute(JACOCO_XML_NODE_COUNTER_ATTRIBUTE_TYPE) == type) {
                val covered = (counter.attribute(JACOCO_XML_NODE_COUNTER_ATTRIBUTE_COVERED) as String).toDouble()
                val missed = (counter.attribute(JACOCO_XML_NODE_COUNTER_ATTRIBUTE_MISSED) as String).toDouble()
                val total = covered + missed
                return (covered / total * HUNDRED_PERCENT).round()
            }
        }
        throw GradleException("Code coverage failure (The counter type will the name '$type' is not available)")
    }

    private fun expectedCoverageMetrics(coverage: File): Properties {
        val expectedCoverageMetrics = Properties()
        expectedCoverageMetrics.load(FileInputStream(coverage))
        return expectedCoverageMetrics
    }

    private fun metricsResults(
        actualCoverageMetrics: Map<String, Double>,
        expectedCoverageMetrics: Properties
    ): Pair<MutableList<String>, MutableList<String>> {
        val successes = mutableListOf<String>()
        val failures = mutableListOf<String>()
        actualCoverageMetrics.forEach {
            val coverageType = it.key
            val actualCoverageMetric = it.value
            val expectedCoverageMetric = expectedCoverageMetrics[coverageType].toString().toDouble().round()
            if (actualCoverageMetric >= expectedCoverageMetric) {
                successes.add(coverageResult(coverageType, actualCoverageMetric, expectedCoverageMetric))
            } else {
                failures.add(coverageResult(coverageType, actualCoverageMetric, expectedCoverageMetric))
            }
        }
        return successes to failures
    }

    private fun coverageResult(
        coverageType: String,
        actualCoverageMetric: Double,
        expectedCoverageMetric: Double
    ): String {
        var coverageTypeSentence = " $DASH $coverageType coverage is: "
        while (coverageTypeSentence.length < COVERAGE_TYPE_SENTENCE_LENGTH) coverageTypeSentence += SPACE
        val actualCoverageMetricSentence = "$actualCoverageMetric$PERCENT"
        val expectedCoverageMetricSentence = "(expecting $expectedCoverageMetric$PERCENT)"
        val diff = actualCoverageMetric - expectedCoverageMetric
        val coverageResult = coverageTypeSentence + SPACE +
                actualCoverageMetricSentence + SPACE +
                expectedCoverageMetricSentence
        return if (diff == 0.0) {
            coverageResult
        } else {
            val diffCoverageMetricSentence = "- diff: " + if (diff > 0.0) "+$diff" else diff
            coverageResult + SPACE +
                    diffCoverageMetricSentence
        }
    }

    private fun printMetricsResults(results: Pair<MutableList<String>, MutableList<String>>) {
        val successes = results.first
        val failures = results.second
        if (failures.isNotEmpty()) {
            logger.quiet("$CODE_COVERAGE_FAILURE_RESULT_INE\n")
            failures.forEach { logger.quiet(it) }
            logger.quiet("\n$CODE_COVERAGE_END_RESULT_LINE")
            throw GradleException("Code coverage failure")
        } else {
            logger.quiet("$CODE_COVERAGE_SUCCESS_RESULT_LINE\n")
            successes.forEach { logger.quiet(it) }
            logger.quiet("\n$CODE_COVERAGE_END_RESULT_LINE")
        }
    }

}

private fun Double.round(decimals: Int = 1): Double = "%.${decimals}f".format(this).toDouble()
