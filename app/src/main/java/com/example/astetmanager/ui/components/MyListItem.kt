package com.example.astetmanager.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.astetmanager.ui.theme.AstetManagerTheme

@Composable
fun MyListItem(
    modifier: Modifier = Modifier,
    header: String,
    body: String,
    onClick: () -> Unit = {},
    Content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = header,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier
                        .padding(4.dp)
                )

                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Content()
        }
    }
}

@Preview
@Composable
fun ListItem_Preview() {
    AstetManagerTheme {
        MyListItem(
            header = "Header",
            body = "Body"
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(48.dp)
            )
        }
    }
}
