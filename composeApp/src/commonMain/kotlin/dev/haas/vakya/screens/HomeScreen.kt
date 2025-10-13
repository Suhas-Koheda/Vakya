package dev.haas.vakya.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.haas.vakya.composables.Book
import dev.haas.vakya.composables.ExpressiveBottomNav

@Composable
fun HomeScreen() {
    var selectedIndex by remember { mutableStateOf(2) }

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
                    else -> HomeScreenView()
                }
            }
        }
    }
}


@Composable
fun HomeScreenView(){
    Box(modifier = Modifier.fillMaxHeight(),){
        Column {
            Row (modifier = Modifier.fillMaxWidth().padding(vertical = 250.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceEvenly){
                Column {
                    Text("Your", style = MaterialTheme.typography.bodyLarge)
                    Text("Thoughts", style = MaterialTheme.typography.bodyLarge)
                }
                FloatingActionButton(
                    onClick = {},
                    shape = MaterialTheme.shapes.extraLarge,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.Red,
                    content = { Icon(Book, contentDescription ="Book Icon") },
                )
            }
            Column {
                Text("Add New thoughts by clicking the button above",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Start).padding(start = 150.dp,end=50.dp))
                Text("Navigate Through the below bar to explore more",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.End).padding(start=50.dp,end=150.dp))
            }
        }
    }
}