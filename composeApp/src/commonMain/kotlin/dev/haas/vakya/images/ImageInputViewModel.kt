package dev.haas.vakya.images

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object   ImageInputViewModel {
    private val _imageBytes = MutableStateFlow<ByteArray?>(null)
    val imageBytes = _imageBytes.asStateFlow()

    fun onImageReceived(bytes: ByteArray) {
        _imageBytes.value = bytes
    }
}
