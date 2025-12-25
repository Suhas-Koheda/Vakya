package dev.haas.vakya.images

import androidx.lifecycle.ViewModel
import dev.haas.vakya.common.ScreenShotProcessorProvider
import dev.haas.vakya.common.implementations.DefaultOcrEngine
import dev.haas.vakya.common.implementations.DefaultScreenShotProcesser
import dev.haas.vakya.common.implementations.PlatformOcrFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Clock

object   ImageInputViewModel : ViewModel() {
    private val processor= ScreenShotProcessorProvider.create()
    private val _imageBytes = MutableStateFlow<ByteArray?>(null)
    val imageBytes = _imageBytes.asStateFlow()

    @OptIn(DelicateCoroutinesApi::class)
    fun onImageReceived(bytes: ByteArray) {
        _imageBytes.value = bytes
        GlobalScope.launch {
            val note=processor.processScreenshot(
                imageBytes=bytes,
                createdAt = 1000L
            )
            println("generated note $note")
        }
    }
}
