package dev.haas.vakya

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.haas.vakya.images.ImageInputViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun App() {

    val imageBytes by ImageInputViewModel.imageBytes.collectAsState()
    val note by ImageInputViewModel.note.collectAsState()

    LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp)
) {
    item {
        Text("Screenshot received: ${imageBytes?.size ?: 0} bytes")
    }
    item {
        note?.let {
            SelectionContainer {
                Text(it.content)
            }
        }
    }
}
}
