package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
data class TextClip(
    val text: String,
    val source: ClipSource
)

@Serializable
enum class ClipSource {
    OCR,
    SUMMARY,
    LINK,
    QUESTION
}
