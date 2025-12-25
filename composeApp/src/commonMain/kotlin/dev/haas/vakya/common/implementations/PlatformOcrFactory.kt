package dev.haas.vakya.common.implementations

import dev.haas.vakya.common.OcrEngine

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object PlatformOcrFactory {
    fun ocrEngine(): OcrEngine
}