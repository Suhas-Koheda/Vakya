package dev.haas.vakya.common

interface OcrEngine {
    suspend fun extractText(imageBytes: ByteArray): String
}