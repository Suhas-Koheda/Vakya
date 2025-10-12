package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
data class ClassificationOutput (
    val type: NoteType,
    val target: String?,
    val confidence: Float
)