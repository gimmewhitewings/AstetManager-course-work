package com.example.astetmanager.ui.screens.complect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.astetmanager.R
import com.example.astetmanager.data.database.entities.enums.PartTypeSize
import com.example.astetmanager.ui.screens.complect.components.ComplectPartsAmountsBlock
import com.example.astetmanager.ui.screens.complect.components.ComplectSizeChooser
import com.example.astetmanager.ui.theme.AstetManagerTheme

@Composable
fun ComplectScreen(
    navController: NavController,
    viewModel: ComplectViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    ComplectScreenContent(
        onNavigationButtonClick = { navController.popBackStack() },
        complectName = viewState.complectName,
        setComplectName = viewModel::setComplectName,
        onClearComplectNameButtonClick = viewModel::clearComplectName,
        complectVendorCode = viewState.complectVendorCode,
        setComplectVendorCode = viewModel::setComplectVendorCode,
        selectedComplectPartTypeSize = viewState.selectedComplectPartTypeSize,
        setComplectSize = viewModel::setSelectedComplectSize,
        pillowcasesAmount = viewState.pillowcasesAmount,
        onAddPillowcaseButtonClick = viewModel::addPillowCase,
        onRemovePillowcaseButtonClick = viewModel::removePillowCase,
        sheetsAmount = viewState.sheetsAmount,
        onAddSheetButtonClick = viewModel::addSheet,
        onRemoveSheetButtonClick = viewModel::removeSheet,
        duvetCoversAmount = viewState.duvetCoversAmount,
        onAddDuvetCoverButtonClick = viewModel::addDuvetCover,
        onRemoveDuvetCoverButtonClick = viewModel::removeDuvetCover,
        onSaveComplectButtonClick = viewModel::saveComplectInfo
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplectScreenContent(
    onNavigationButtonClick: () -> Unit = {},
    complectName: String = "",
    setComplectName: (String) -> Unit = {},
    onClearComplectNameButtonClick: () -> Unit = {},
    complectVendorCode: String = "",
    setComplectVendorCode: (String) -> Unit = {},
    selectedComplectPartTypeSize: PartTypeSize = PartTypeSize.M,
    setComplectSize: (PartTypeSize) -> Unit = {},
    pillowcasesAmount: Int = 0,
    onAddPillowcaseButtonClick: () -> Unit = {},
    onRemovePillowcaseButtonClick: () -> Unit = {},
    sheetsAmount: Int = 0,
    onAddSheetButtonClick: () -> Unit = {},
    onRemoveSheetButtonClick: () -> Unit = {},
    duvetCoversAmount: Int = 0,
    onAddDuvetCoverButtonClick: () -> Unit = {},
    onRemoveDuvetCoverButtonClick: () -> Unit = {},
    onSaveComplectButtonClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.complect_record)) },
                navigationIcon = {
                    IconButton(onClick = onNavigationButtonClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(144.dp)
                        .clip(shape = RoundedCornerShape(20))
                        .background(color = Color.Gray)
                )
                // Row of text fields
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Name text field
                    OutlinedTextField(
                        value = complectName,
                        onValueChange = setComplectName,
                        label = { Text(text = stringResource(id = R.string.name)) },
                        modifier = Modifier.weight(2f),
                        singleLine = true,
                        trailingIcon = {
                            if (complectName.isNotEmpty()) {
                                IconButton(onClick = onClearComplectNameButtonClick) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                    // Code text field
                    OutlinedTextField(
                        value = complectVendorCode,
                        onValueChange = setComplectVendorCode,
                        singleLine = true,
                        label = { Text(text = stringResource(id = R.string.vendor_code)) },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Add cloth button
                FilledTonalIconButton(
                    onClick = { /*TODO*/ },
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Text(text = stringResource(id = R.string.cloth))
                    }
                }

                ComplectSizeChooser(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    selectedComplectSize = selectedComplectPartTypeSize,
                    setComplectSize = setComplectSize
                )

                ComplectPartsAmountsBlock(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    pillowcasesAmount = pillowcasesAmount,
                    onRemovePillowcaseButtonClick = onRemovePillowcaseButtonClick,
                    onAddPillowcaseButtonClick = onAddPillowcaseButtonClick,
                    sheetsAmount = sheetsAmount,
                    onRemoveSheetButtonClick = onRemoveSheetButtonClick,
                    onAddSheetButtonClick = onAddSheetButtonClick,
                    duvetCoversAmount = duvetCoversAmount,
                    onRemoveDuvetCoverButtonClick = onRemoveDuvetCoverButtonClick,
                    onAddDuvetCoverButtonClick = onAddDuvetCoverButtonClick
                )
            }

            // Save button
            Button(
                onClick = onSaveComplectButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}

@Preview
@Composable
fun ComplectScreenContent_Preview() {
    AstetManagerTheme {
        ComplectScreenContent()
    }
}
