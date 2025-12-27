package dev.haas.vakya.common.implementations

import dev.haas.vakya.common.ScreenshotProcessor

object ScreenshotProcessorProvider {
    fun create(): ScreenshotProcessor {
        return DefaultScreenshotProcessor(
            ocrEngine = PlatformOcrFactory.ocrEngine()
        )
    }
}