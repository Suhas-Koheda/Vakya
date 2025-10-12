package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
data class Note (
    val id:String,
    val content:String,
    val type:NoteType,
    val targetBucket:String,
    val createdAt:Long,
    val targetDate:Long? = null,
    val isCompleted:Boolean? = false
)