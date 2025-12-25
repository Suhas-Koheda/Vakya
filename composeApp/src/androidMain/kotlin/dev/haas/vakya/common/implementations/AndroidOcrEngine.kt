package dev.haas.vakya.common.implementations

import android.graphics.BitmapFactory
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dev.haas.vakya.common.OcrEngine
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AndroidOcrEngine : OcrEngine {

    override suspend fun extractText(imageBytes: ByteArray): String =
        suspendCancellableCoroutine { cont ->
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                ?: run {
                    cont.resume("")
                    return@suspendCancellableCoroutine
                }
            val image = InputImage.fromBitmap(bitmap, 0)
            val recognizer =
                TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            recognizer.process(image)
                .addOnSuccessListener { result ->
                    cont.resume(result.text ?: "")
                }
                .addOnFailureListener { e ->
                    cont.resumeWithException(e)
                }
        }
}
