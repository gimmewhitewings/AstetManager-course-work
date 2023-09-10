package com.example.astetmanager.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.astetmanager.R

@Composable
fun AddingDialog(
    onDismissRequest: () -> Unit,
    leftButtonText: String,
    leftButtonOnClick: () -> Unit,
    rightButtonText: String,
    rightButtonOnClick: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.add_question),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(onClick = leftButtonOnClick) {
                        Text(text = leftButtonText)
                    }
                    OutlinedButton(onClick = rightButtonOnClick) {
                        Text(text = rightButtonText)
                    }
                }
            }
        }
    }
}
