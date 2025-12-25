package dev.haas.vakya

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import dev.haas.vakya.images.ImageInputViewModel
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun App(){
    val imageBytes by ImageInputViewModel.imageBytes.collectAsState()
    if (imageBytes == null) {
        Text("No screenshot received")
    } else {
        Text("Screenshot received: ${imageBytes!!.size} bytes")
    }
}