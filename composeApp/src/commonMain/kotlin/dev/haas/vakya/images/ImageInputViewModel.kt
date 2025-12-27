package dev.haas.vakya.images

import dev.haas.vakya.common.implementations.ScreenshotProcessorProvider
import dev.haas.vakya.models.ScreenshotProcessingResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object ImageInputViewModel {

    private val processor = ScreenshotProcessorProvider.create()

    private val _imageBytes = MutableStateFlow<ByteArray?>(null)
    val imageBytes = _imageBytes.asStateFlow()

    private val _processingResult = MutableStateFlow<ScreenshotProcessingResult?>(null)
    val processingResult = _processingResult.asStateFlow()

    fun onImageReceived(bytes: ByteArray) {
        _imageBytes.value = bytes
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        scope.launch {
            val result = processor.processScreenshot(
                imageBytes = bytes,
                createdAt = 1000L, // TODO: Use a real timestamp
            )
            _processingResult.value = result
        }
    }
}
