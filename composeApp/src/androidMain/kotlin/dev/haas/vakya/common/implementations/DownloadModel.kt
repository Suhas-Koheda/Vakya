package dev.haas.vakya.common.implementations

import android.content.Context
import java.io.File
import java.net.URL
import java.nio.ByteBuffer
import java.nio.ByteOrder

fun downloadModelIfNeeded(
    context: Context,
    url: String
): File {

    val modelDir = File(context.filesDir, "models")
    if (!modelDir.exists()) modelDir.mkdirs()

    val modelFile = File(modelDir, "sentence_ranker.tflite")

    if (modelFile.exists()) return modelFile

    URL(url).openStream().use { input ->
        modelFile.outputStream().use { output ->
            input.copyTo(output)
        }
    }

    return modelFile
}

fun loadModelFromFile(file: File): ByteBuffer {
    val bytes = file.readBytes()

    return ByteBuffer
        .allocateDirect(bytes.size)
        .order(ByteOrder.nativeOrder())
        .apply {
            put(bytes)
            rewind()
        }
}
