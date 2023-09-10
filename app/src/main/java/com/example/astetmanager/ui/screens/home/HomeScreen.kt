package com.example.astetmanager.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.astetmanager.R
import com.example.astetmanager.ui.components.AddButton
import com.example.astetmanager.ui.theme.AstetManagerTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    HomeScreenContent(
        onStorageAddButtonClick = { /*TODO*/ },
        onDocumentsAddButtonClick = { /*TODO*/ }
    )
}

@Composable
fun HomeScreenContent(
    onStorageAddButtonClick: () -> Unit,
    onDocumentsAddButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Tasks section
        Text(
            text = stringResource(id = R.string.upcoming_event),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        HorizontalDivider(modifier = Modifier.padding(16.dp))

        // Storage section
        Text(
            text = stringResource(id = R.string.storage),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(id = R.string.storage_summary, 50, 2),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        AddButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            buttonText = stringResource(id = R.string.add)
        )

        HorizontalDivider(modifier = Modifier.padding(16.dp))

        // Documents section
        Text(
            text = stringResource(id = R.string.documents),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = stringResource(id = R.string.last_application),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = stringResource(id = R.string.last_implementation),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 12.dp)
        )

        AddButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            buttonText = stringResource(id = R.string.add)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContent_Preview() {
    AstetManagerTheme {
        HomeScreenContent(
            onStorageAddButtonClick = {},
            onDocumentsAddButtonClick = {}
        )
    }
}
