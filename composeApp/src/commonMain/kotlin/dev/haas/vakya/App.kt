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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.haas.vakya.composables.Book
import dev.haas.vakya.composables.ExpressiveBottomNav
import dev.haas.vakya.composables.Library_books
import dev.haas.vakya.screens.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
            HomeScreen()
    }
}