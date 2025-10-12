package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
data class NoteBucket (
    val target:String,
    val notes: List<Note>
)