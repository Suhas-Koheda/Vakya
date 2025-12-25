package dev.haas.vakya.common

import dev.haas.vakya.common.implementations.DefaultScreenShotProcesser
import dev.haas.vakya.common.implementations.PlatformOcrFactory

object ScreenShotProcessorProvider {
    fun create(): ScreenShotProcessor{
        return DefaultScreenShotProcesser(
            ocrEngine = PlatformOcrFactory.ocrEngine()
        )
    }
}