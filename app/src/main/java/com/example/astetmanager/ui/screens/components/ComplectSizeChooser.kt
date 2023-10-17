package com.example.astetmanager.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.astetmanager.data.database.entities.enums.PartTypeSize

@Composable
fun ComplectSizeChooser(
    modifier: Modifier,
    selectedComplectSize: PartTypeSize,
    setComplectSize: (PartTypeSize) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        PartTypeSize.entries.forEach { size ->
            val isSelected = (size == selectedComplectSize)
            AssistChip(
                onClick = { setComplectSize(size) },
                label = { Text(text = size.name) },
                shape = CircleShape,
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (isSelected) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        AssistChipDefaults.assistChipColors().containerColor
                    },
                    labelColor = if (isSelected) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        AssistChipDefaults.assistChipColors().labelColor
                    }
                )
            )
        }
    }
}
