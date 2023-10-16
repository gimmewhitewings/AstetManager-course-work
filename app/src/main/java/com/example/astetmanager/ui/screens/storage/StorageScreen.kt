package com.example.astetmanager.ui.screens.storage

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.astetmanager.R
import com.example.astetmanager.data.database.entities.enums.PartTypeClass
import com.example.astetmanager.data.database.entities.enums.PartTypeSize
import com.example.astetmanager.data.database.entities.enums.getStringResourceId
import com.example.astetmanager.ui.screens.complect.components.PartTypeClassAmountChooser
import com.example.astetmanager.ui.screens.complect.components.ComplectPartsAmountsBlock
import com.example.astetmanager.ui.screens.complect.components.ComplectSizeChooser
import kotlinx.coroutines.launch

@Composable
fun StorageScreen(
    viewModel: StorageViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    StorageScreenContent(
        onAddNewComplectButtonClick = { },
        onAddPartTypeButtonClick = { },
        addingPartTypeClassAmount = viewState.addingPartTypeClassAmount,
        increaseAddingPartTypeClassAmount = viewModel::increaseAddingPartTypeClassAmount,
        decreaseAddingPartClassAmount = viewModel::decreaseAddingPartTypeClassAmount,
        selectedAddingPartTypeClass = viewState.selectedAddingPartTypeClass,
        selectAddingPartTypeClass = viewModel::selectAddingPartTypeClass,
        pillowcasesAmount = viewState.pillowcasesAmount,
        sheetsAmount = viewState.sheetsAmount,
        duvetCoversAmount = viewState.duvetCoversAmount,
        onRemovePillowcaseButtonClick = viewModel::removePillowCase,
        onAddPillowcaseButtonClick = viewModel::addPillowCase,
        onRemoveSheetButtonClick = viewModel::removeSheet,
        onAddSheetButtonClick = viewModel::addSheet,
        onRemoveDuvetCoverButtonClick = viewModel::removeDuvetCover,
        onAddDuvetCoverButtonClick = viewModel::addDuvetCover,
        selectedPartTypeSize = viewState.selectedPartTypeSize,
        setPartTypeSize = viewModel::setSelectedPartTypeSize
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageScreenContent(
    onAddNewComplectButtonClick: () -> Unit = {},
    onAddPartTypeButtonClick: () -> Unit = {},
    addingPartTypeClassAmount: Int,
    increaseAddingPartTypeClassAmount: () -> Unit,
    decreaseAddingPartClassAmount: () -> Unit,
    selectedAddingPartTypeClass: PartTypeClass,
    selectAddingPartTypeClass: (PartTypeClass) -> Unit,
    pillowcasesAmount: Int,
    sheetsAmount: Int,
    duvetCoversAmount: Int,
    onRemovePillowcaseButtonClick: () -> Unit,
    onAddPillowcaseButtonClick: () -> Unit,
    onRemoveSheetButtonClick: () -> Unit,
    onAddSheetButtonClick: () -> Unit,
    onRemoveDuvetCoverButtonClick: () -> Unit,
    onAddDuvetCoverButtonClick: () -> Unit,
    selectedPartTypeSize: PartTypeSize,
    setPartTypeSize: (PartTypeSize) -> Unit
) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf(
        stringResource(id = R.string.all),
        stringResource(id = R.string.cloth),
        stringResource(id = R.string.complects)
    )
    var selectedAddingTabIndex by remember { mutableIntStateOf(0) }
    var searchText by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    val searchBarPadding by animateDpAsState(
        targetValue = if (isSearchActive) 0.dp else 16.dp,
        label = "searchBarPadding"
    )
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = searchBarPadding),
                    query = searchText,
                    onQueryChange = { searchText = it },
                    onSearch = { isSearchActive = false },
                    active = isSearchActive,
                    onActiveChange = { isSearchActive = it },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    trailingIcon = {
                        if (isSearchActive) {
                            IconButton(
                                onClick = {
                                    if (searchText.isNotEmpty()) {
                                        searchText = ""
                                    } else {
                                        isSearchActive = false
                                    }
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                    content = {
                        // TODO: implement search
                    }
                )
                TabRow(
                    selectedTabIndex = state
                ) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = state == index,
                            onClick = { state = index },
                            text = {
                                Text(
                                    text = title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        )
                    }
                }
            }
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            TabRow(selectedTabIndex = selectedAddingTabIndex) {
                AddingTab.entries.forEachIndexed { index, addingTab ->
                    Tab(
                        selected = index == selectedAddingTabIndex,
                        onClick = { selectedAddingTabIndex = index }
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = stringResource(addingTab.getStringResourceId()),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            ComplectSizeChooser(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                selectedComplectSize = selectedPartTypeSize,
                setComplectSize = setPartTypeSize
            )

            if (selectedAddingTabIndex == AddingTab.PART_TYPE.ordinal) {
                val horizontalScrollState = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(horizontalScrollState),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PartTypeClass.entries.forEach { partTypeClass ->
                        FilterChip(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            selected = partTypeClass == selectedAddingPartTypeClass,
                            onClick = { selectAddingPartTypeClass(partTypeClass) },
                            label = { Text(text = stringResource(id = partTypeClass.getStringResourceId())) }
                        )
                    }
                }
                PartTypeClassAmountChooser(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(selectedAddingPartTypeClass.getStringResourceId()),
                    counterValue = addingPartTypeClassAmount,
                    onRemoveButtonClick = decreaseAddingPartClassAmount,
                    onAddButtonClick = increaseAddingPartTypeClassAmount
                )
            } else {
                ComplectPartsAmountsBlock(
                    modifier = Modifier.padding(horizontal = 16.dp),
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
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = when (selectedAddingTabIndex) {
                    AddingTab.PART_TYPE.ordinal -> onAddPartTypeButtonClick
                    AddingTab.COMPLECT.ordinal -> onAddNewComplectButtonClick
                    else -> {
                        throw IllegalArgumentException()
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.add))
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter),
                text = "Text tab ${state + 1} selected",
                style = MaterialTheme.typography.bodyLarge
            )
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}


//@Preview(showBackground = true)
//@Preview(showBackground = true, locale = "ru", name = "Russian")
//@Composable
//fun StorageScreenContent_Preview() {
//    AstetManagerTheme {
//        StorageScreenContent()
//    }
//}
