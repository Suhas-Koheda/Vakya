package dev.haas.vakya.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.haas.vakya.common.ScreenShotProcessorProvider
import dev.haas.vakya.models.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Clock

object ImageInputViewModel  {

    private val processor = ScreenShotProcessorProvider.create()

    private val _imageBytes = MutableStateFlow<ByteArray?>(null)
    val imageBytes = _imageBytes.asStateFlow()

    private val _note = MutableStateFlow<Note?>(null)
    val note = _note.asStateFlow()

    @OptIn(DelicateCoroutinesApi::class)
    fun onImageReceived(bytes: ByteArray) {
        _imageBytes.value = bytes
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        scope.launch {

            val generatedNote = processor.processScreenshot(
                imageBytes = bytes,
                createdAt = 1000L
            )
            _note.value = generatedNote
            println("generated note $generatedNote")
        }
    }
}
