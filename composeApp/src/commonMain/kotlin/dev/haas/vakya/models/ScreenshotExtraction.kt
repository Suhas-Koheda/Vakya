package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotExtraction(
    val screenshotId: String,
    val rawText: String,
    val summary: String? = null,
    val links: List<String> = emptyList(),
    val questions: List<String> = emptyList(),
    val todos: List<String> = emptyList(),
    val confidence: Float
)
