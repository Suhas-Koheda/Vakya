package dev.haas.vakya.images

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ReceiveScreenshot{
    suspend fun receiveImageBytes(): ByteArray?
}