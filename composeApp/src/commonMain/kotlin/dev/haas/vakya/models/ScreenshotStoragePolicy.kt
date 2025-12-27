package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
enum class ScreenshotStoragePolicy {
    DISCARD_AFTER_PROCESSING,
    KEEP_LOCALLY,
    ATTACH_TO_NOTE
}
