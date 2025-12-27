package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotProcessingResult(
    val asset: ScreenshotAsset,
    val extraction: ScreenshotExtraction,
    val suggestedActions: List<ScreenshotAction>
)
