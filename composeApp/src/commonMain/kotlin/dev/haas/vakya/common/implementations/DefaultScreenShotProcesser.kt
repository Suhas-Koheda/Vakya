package dev.haas.vakya.common.implementations

import dev.haas.vakya.common.OcrEngine
import dev.haas.vakya.common.ScreenShotProcessor
import dev.haas.vakya.models.Note
import dev.haas.vakya.models.NoteType
import kotlin.math.log
import kotlin.random.Random

class DefaultScreenShotProcesser(private val ocrEngine: OcrEngine): ScreenShotProcessor {
    override suspend fun processScreenshot(imageBytes: ByteArray,
                                           createdAt: Long): Note {
        val rawText=ocrEngine.extractText(imageBytes)
        val cleaned=rawText.trim();
        require(cleaned.length >= 10)
        return Note(
            id = Random.Default.nextInt().toString(),
            content = cleaned,
            type = NoteType.Note,
            targetBucket = "Inbox",
            createdAt = createdAt
        )
    }
}