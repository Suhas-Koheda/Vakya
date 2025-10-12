package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
sealed class NoteType {
    @Serializable object Task : NoteType()
    @Serializable object Idea : NoteType()
    @Serializable object Note : NoteType()
}