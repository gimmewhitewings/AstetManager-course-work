package com.example.astetmanager.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.astetmanager.R

@Composable
fun PartTypeClassAmountChooser(
    modifier: Modifier,
    text: String,
    counterValue: Int,
    onRemoveButtonClick: () -> Unit,
    onAddButtonClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(text = text)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onRemoveButtonClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
                    contentDescription = null
                )
            }
            Text(text = "$counterValue")
            IconButton(onClick = onAddButtonClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    }
}
