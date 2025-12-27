package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotAsset(
    val id: String,
    val source: ScreenshotSource,
    val createdAt: Long,
    val resolution: Resolution? = null,
    val isPersisted: Boolean = false,
    val localRef: String? = null // file path / content URI / iOS identifier
)

@Serializable
enum class ScreenshotSource {
    SHARE,
    GALLERY,
    CAMERA
}

@Serializable
data class Resolution(
    val width: Int,
    val height: Int
)
