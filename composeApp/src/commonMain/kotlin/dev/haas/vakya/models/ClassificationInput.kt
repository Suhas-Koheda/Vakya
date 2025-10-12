package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
data class ClassificationInput (
    val content:String,
    val buckets:List<String>
)