package dev.haas.vakya.common.implementations

import dev.haas.vakya.common.OcrEngine
import dev.haas.vakya.common.ScreenshotProcessor
import dev.haas.vakya.common.SentenceSummarizer
import dev.haas.vakya.models.ScreenshotAction
import dev.haas.vakya.models.ScreenshotAsset
import dev.haas.vakya.models.ScreenshotExtraction
import dev.haas.vakya.models.ScreenshotProcessingResult
import dev.haas.vakya.models.ScreenshotSource
import kotlin.random.Random

class DefaultScreenshotProcessor(
    private val ocrEngine: OcrEngine
) : ScreenshotProcessor {

    private data class DestructuredData(
        val summary: String,
        val links: List<String>,
        val questions: List<String>,
        val todos: List<String>
    )

    private fun destructure(llmOutput: String): DestructuredData {
        val sections = llmOutput.split("---").map { it.trim() }
        var summary = ""
        var links = emptyList<String>()
        var questions = emptyList<String>()
        var todos = emptyList<String>()

        sections.forEach { section ->
            when {
                section.startsWith("SUMMARY:") -> {
                    summary = section.removePrefix("SUMMARY:").trim()
                }
                section.startsWith("LINKS:") -> {
                    links = section.removePrefix("LINKS:").trim().lines().filter { it.isNotBlank() }
                }
                section.startsWith("QUESTIONS:") -> {
                    questions = section.removePrefix("QUESTIONS:").trim().lines().filter { it.isNotBlank() }
                }
                section.startsWith("TODOS:") -> {
                    todos = section.removePrefix("TODOS:").trim().lines().filter { it.isNotBlank() }
                }
            }
        }
        return DestructuredData(summary, links, questions, todos)
    }


    override suspend fun processScreenshot(
        imageBytes: ByteArray,
        createdAt: Long,
    ): ScreenshotProcessingResult {

        val rawText = ocrEngine.extractText(imageBytes)
        val structuredOutput = SentenceSummarizer.summarize(rawText)
        val destructured = destructure(structuredOutput)

        val asset = ScreenshotAsset(
            id = Random.nextInt().toString(),
            source = ScreenshotSource.SHARE, // Assuming SHARE for now
            createdAt = createdAt
        )

        val extraction = ScreenshotExtraction(
            screenshotId = asset.id,
            rawText = rawText,
            summary = destructured.summary,
            links = destructured.links,
            questions = destructured.questions,
            todos = destructured.todos,
            confidence = 0.9f // Placeholder
        )

        val suggestedActions = mutableListOf<ScreenshotAction>()
        if (extraction.summary?.isNotBlank() == true) {
            suggestedActions.add(ScreenshotAction.Summarize)
        }
        if (extraction.links.isNotEmpty()) {
            suggestedActions.add(ScreenshotAction.ExtractLinks)
        }
        if (extraction.questions.isNotEmpty()) {
            suggestedActions.add(ScreenshotAction.ExtractQuestions)
        }
        if (extraction.todos.isNotEmpty()) {
            suggestedActions.add(ScreenshotAction.ExtractTodos)
        }
        // default action
        suggestedActions.add(ScreenshotAction.CreateNote(null))

        print(extraction.summary)
        return ScreenshotProcessingResult(
            asset = asset,
            extraction = extraction,
            suggestedActions = suggestedActions
        )
    }
}
