package com.example.astetmanager.ui.screens.cloth.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.astetmanager.R
import com.example.astetmanager.ui.theme.AstetManagerTheme

@Composable
fun ClothListItem(
    modifier: Modifier = Modifier,
    onDeleteButtonClick: () -> Unit,
    lengthInMeters: Int
) {
    ElevatedCard(
        modifier = modifier
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = stringResource(id = R.string.meters, lengthInMeters),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            trailingContent = {
                IconButton(onClick = onDeleteButtonClick) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewClothListItem() {
    AstetManagerTheme {
        ClothListItem(
            onDeleteButtonClick = {},
            lengthInMeters = 5
        )
    }
}
