package dev.haas.vakya.common.implementations

import dev.haas.vakya.common.OcrEngine

class DefaultOcrEngine: OcrEngine {
    override suspend fun extractText(imageBytes: ByteArray): String {
        return """
            Buy milk tomorrow
            Call John
            Finish Vakya Phase 2
        """.trimIndent()
    }
}