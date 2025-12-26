package dev.haas.vakya.common

import dev.haas.vakya.models.Note

interface ScreenShotProcessor {
    suspend fun processScreenshot(
        imageBytes: ByteArray,
        createdAt: Long,
        llmInference: Any
    ): Note
}