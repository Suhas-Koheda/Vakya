package dev.haas.vakya.common.implementations

import dev.haas.vakya.common.OcrEngine
import dev.haas.vakya.common.ScreenShotProcessor
import dev.haas.vakya.common.SentenceSummarizer
import dev.haas.vakya.models.Note
import dev.haas.vakya.models.NoteType
import kotlin.random.Random

class DefaultScreenShotProcesser(
    private val ocrEngine: OcrEngine
) : ScreenShotProcessor {
    override suspend fun processScreenshot(
        imageBytes: ByteArray,
        createdAt: Long,
    ): Note {

        val rawText = ocrEngine.extractText(imageBytes)
        val summarizedText= SentenceSummarizer.summarize(rawText)
        require(summarizedText.length >= 10)
        return Note(
            id = Random.nextInt().toString(),
            content = summarizedText,
            type = NoteType.Note,
            targetBucket = "Inbox",
            createdAt = createdAt
        )
    }
}
