package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
data class NoteBuckets (
    val buckets: List<NoteBucket>
)