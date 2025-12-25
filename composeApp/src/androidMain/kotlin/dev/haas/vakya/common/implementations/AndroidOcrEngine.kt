package dev.haas.vakya.common.implementations

import dev.haas.vakya.common.OcrEngine

class AndroidOcrEngine : OcrEngine{
    override suspend fun extractText(imageBytes: ByteArray): String {
        TODO("Not yet implemented")
    }
}