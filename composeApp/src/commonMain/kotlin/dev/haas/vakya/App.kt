package dev.haas.vakya

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.haas.vakya.images.ImageInputViewModel
import dev.haas.vakya.models.ScreenshotAction

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun App() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val imageBytes by ImageInputViewModel.imageBytes.collectAsState()
            val processingResult by ImageInputViewModel.processingResult.collectAsState()

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())
            ) {
                Text("Screenshot received: ${imageBytes?.size ?: 0} bytes")

                if (imageBytes != null && processingResult == null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Processing...")
                }

                processingResult?.let { result ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Summary:", style = MaterialTheme.typography.headlineSmall)
                    SelectionContainer {
                        Text(result.extraction.summary ?: "No summary available.")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Suggested Actions:", style = MaterialTheme.typography.headlineSmall)
                    result.suggestedActions.forEach { action ->
                        when (action) {
                            is ScreenshotAction.Summarize -> Button(onClick = {}) { Text("Summarize") }
                            is ScreenshotAction.ExtractLinks -> Button(onClick = {}) { Text("Extract Links") }
                            is ScreenshotAction.ExtractQuestions -> Button(onClick = {}) { Text("Extract Questions") }
                            is ScreenshotAction.ExtractTodos -> Button(onClick = {}) { Text("Extract Todos") }
                            is ScreenshotAction.CreateNote -> Button(onClick = {}) { Text("Create Note") }
                        }
                    }
                }
            }
        }
    }
}
