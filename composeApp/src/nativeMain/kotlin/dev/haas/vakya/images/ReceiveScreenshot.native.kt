package dev.haas.vakya.images

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import platform.Foundation.NSData
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation

actual class ReceiveScreenshot(
    private val image: UIImage
){
    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun receiveImageBytes(): ByteArray?= withContext(Dispatchers.Default){
        val data: NSData = UIImagePNGRepresentation(image) ?: return@withContext null
        ByteArray(data.length.toInt()).also {
            platform.posix.memcpy(it.refTo(0), data.bytes, data.length)
        }
    }
}