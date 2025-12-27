package dev.haas.vakya.common

import dev.haas.vakya.models.ScreenshotProcessingResult

interface ScreenshotProcessor {
    suspend fun processScreenshot(
        imageBytes: ByteArray,
        createdAt: Long,
    ): ScreenshotProcessingResult
}