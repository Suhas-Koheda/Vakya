package dev.haas.vakya.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
                                    modifier = Modifier.size(20.dp),
                                    tint = if (isSelected)
                                        Color.Black else
                                        Color.LightGray
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = if (isSelected)
                                        Color.Black else
                                        Color.LightGray
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}