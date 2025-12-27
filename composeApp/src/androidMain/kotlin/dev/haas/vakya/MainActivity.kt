package dev.haas.vakya

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import dev.haas.vakya.images.ImageInputViewModel
import dev.haas.vakya.images.ReceiveScreenshot
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        AppContextHolder.init(application)
        setContent {
            App()
        }
        handleShareIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleShareIntent(intent)
    }

    private fun handleShareIntent(intent: Intent) {
        if (intent.action == Intent.ACTION_SEND && intent.type?.startsWith("image/") == true) {
            val uri: Uri? = intent.getParcelableExtra(Intent.EXTRA_STREAM)
            if (uri == null) return

            val receiver = ReceiveScreenshot(this, uri)
            lifecycleScope.launch {
                val bytes = receiver.receiveImageBytes()
                Log.d("Vakya", "Image bytes size = ${bytes?.size}")
                if (bytes != null) {
                    ImageInputViewModel.onImageReceived(bytes)
                }
            }
        }
    }
}