package com.example.astetmanager.ui.screens.storage

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.astetmanager.R
import com.example.astetmanager.Screen
import com.example.astetmanager.data.database.entities.enums.PartTypeClass
import com.example.astetmanager.data.database.entities.enums.getStringResourceId
import com.example.astetmanager.ui.theme.AstetManagerTheme
import kotlinx.coroutines.launch

@Composable
fun StorageScreen(
    navController: NavController,
    viewModel: StorageViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    StorageScreenContent(
        onAddNewComplectButtonClick = {  },
        onAddPartTypeButtonClick = { }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageScreenContent(
    onAddNewComplectButtonClick: () -> Unit = {},
    onAddPartTypeButtonClick: () -> Unit = {}
) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf(
        stringResource(id = R.string.all),
        stringResource(id = R.string.cloth),
        stringResource(id = R.string.complects)
    )
    var selectedAddingTabIndex by remember { mutableIntStateOf(0) }
    var selectedAddingPartTypeIndex by remember { mutableIntStateOf(0) }
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
                    content = {}
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

            if (selectedAddingTabIndex == AddingTab.PART_TYPE.ordinal) {
                val horizontalScrollState = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(horizontalScrollState)
                        .padding(vertical = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PartTypeClass.entries.forEachIndexed { index, partTypeClass ->
                        FilterChip(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            selected = index == selectedAddingPartTypeIndex,
                            onClick = { selectedAddingPartTypeIndex = index },
                            label = { Text(text = stringResource(id = partTypeClass.getStringResourceId())) }
                        )
                    }
                }
            }
            ElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }
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

enum class AddingTab {
    PART_TYPE,
    COMPLECT
}

fun AddingTab.getStringResourceId(): Int {
    return when (this) {
        AddingTab.PART_TYPE -> R.string.part_type
        AddingTab.COMPLECT -> R.string.complect
    }
}

@Preview(showBackground = true)
@Composable
fun StorageScreenContent_Preview() {
    AstetManagerTheme {
        StorageScreenContent()
    }
}
