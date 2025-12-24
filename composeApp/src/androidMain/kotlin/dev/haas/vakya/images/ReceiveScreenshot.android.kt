package dev.haas.vakya.images

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

actual class ReceiveScreenshot(
    private val context: Context,
    private val imageUri: Uri
){
    actual suspend fun receiveImageBytes(): ByteArray?= withContext(Dispatchers.IO){
        val resolver:ContentResolver=context.contentResolver
        resolver.openInputStream(imageUri)?.use {input->
            val bitMap= BitmapFactory.decodeStream(input)?:return@withContext null
            val out = ByteArrayOutputStream()
            bitMap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.toByteArray()
        }
    }
}