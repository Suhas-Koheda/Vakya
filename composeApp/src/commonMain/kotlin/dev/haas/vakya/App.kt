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
import dev.haas.vakya.composables.Library_books
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var selectedIndex by remember { mutableStateOf(0) }

        Scaffold(
            bottomBar = {
                ExpressiveBottomNav(
                    selectedIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = selectedIndex,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(250)) togetherWith
                                fadeOut(animationSpec = tween(250))
                    },
                    label = "Animated Screen Change"
                ) { index ->
                    when (index) {
                        0 -> NotesScreen()
                        1 -> BucketsScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun NotesScreen() {
    Text("📘 Notes Screen", style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun BucketsScreen() {
    Text("🪣 Buckets Screen", style = MaterialTheme.typography.headlineSmall)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpressiveBottomNav(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf("Notes", "Buckets")
    val icons = listOf(Library_books, Book)

    Surface(
        modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.height(70.dp)
            ) {
                items.forEachIndexed { index, label ->
                    val isSelected = selectedIndex == index

                    SegmentedButton(
                        selected = false, // remove default tick
                        onClick = { onItemSelected(index) },
                        shape = SegmentedButtonDefaults.itemShape(index, items.size),
                        modifier = Modifier
                            .background(
                                brush = if (isSelected) {
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFFFF4B2B), Color(0xFFFF416C))
                                    )
                                } else {
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.surface,
                                            MaterialTheme.colorScheme.surface
                                        )
                                    )
                                },
                                shape = SegmentedButtonDefaults.itemShape(index, items.size)
                            ),
                        label = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                            ) {
                                Icon(
                                    imageVector = icons[index],
                                    contentDescription = label,
                                    modifier = Modifier.size(24.dp),
                                    tint = if (isSelected)
                                        Color.Black else
                                        Color.Cyan
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = if (isSelected) Color.Black
                                    else Color.Cyan
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}