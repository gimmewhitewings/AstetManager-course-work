package com.example.astetmanager.ui.screens.orders

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.astetmanager.data.database.entities.Order
import com.example.astetmanager.data.database.entities.enums.Counterparty
import com.example.astetmanager.data.database.entities.enums.OrderStatus
import com.example.astetmanager.data.database.entities.enums.PaymentMethod
import com.example.astetmanager.data.database.entities.enums.getStringResourceId
import com.example.astetmanager.ui.Screen
import com.example.astetmanager.ui.theme.AstetManagerTheme

@Composable
fun OrdersScreen(
    navController: NavController,
    viewModel: OrdersViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    OrdersScreenContent(
        ordersUiStates = viewState.orderUiStates,
        onAddNewOrderButtonClick = { navController.navigate(Screen.NewOrder.route) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreenContent(
    ordersUiStates: List<OrderUiState>,
    onAddNewOrderButtonClick: () -> Unit = {},
) {
//    var selectedTabIndex by remember { mutableIntStateOf(0) }
//    val titles = listOf(
//        stringResource(id = R.string.all),
//        stringResource(id = R.string.applications),
//        stringResource(id = R.string.implementations)
//    )
//    var searchText by remember { mutableStateOf("") }
//    var isSearchActive by remember { mutableStateOf(false) }
//    val searchBarPadding by animateDpAsState(targetValue = if (isSearchActive) 0.dp else 16.dp)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                text = stringResource(id = R.string.orders),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 24.dp, horizontal = 12.dp)
            )
//            Column {
//                SearchBar(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = searchBarPadding),
//                    query = searchText,
//                    onQueryChange = { searchText = it },
//                    onSearch = { isSearchActive = false },
//                    active = isSearchActive,
//                    onActiveChange = { isSearchActive = it },
//                    leadingIcon = {
//                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
//                    },
//                    trailingIcon = {
//                        if (isSearchActive) {
//                            IconButton(
//                                onClick = {
//                                    if (searchText.isNotEmpty()) {
//                                        searchText = ""
//                                    } else {
//                                        isSearchActive = false
//                                    }
//                                }
//                            ) {
//                                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
//                            }
//                        }
//                    },
//                    content = {}
//                )
//                TabRow(
//                    selectedTabIndex = selectedTabIndex
//                ) {
//                    titles.forEachIndexed { index, title ->
//                        Tab(
//                            selected = selectedTabIndex == index,
//                            onClick = { selectedTabIndex = index },
//                            text = {
//                                Text(
//                                    text = title,
//                                    maxLines = 1,
//                                    overflow = TextOverflow.Ellipsis
//                                )
//                            }
//                        )
//                    }
//                }
//            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNewOrderButtonClick
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(ordersUiStates) { orderUiState ->
                OrderItem(
                    modifier = Modifier.fillMaxWidth(),
                    counterparty = orderUiState.counterparty,
                    paymentMethod = orderUiState.paymentMethod,
                    articular = orderUiState.articular,
                    tasksCount = orderUiState.tasksCount,
                    status = orderUiState.status
                )
            }
        }
    }
}

@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    counterparty: Counterparty,
    paymentMethod: PaymentMethod,
    articular: String?,
    tasksCount: Int?,
    status: OrderStatus
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            articular?.let { Text(text = it) }
            Text(text = "Задачи $tasksCount")
            Text(text = stringResource(id = counterparty.getStringResourceId()))
            Text(text = stringResource(id = paymentMethod.getStringResourceId()))
        }
    }
}

//@Preview
//@Composable
//fun DocumentScreenContent_Preview() {
//    AstetManagerTheme {
//        OrdersScreenContent()
//    }
//}
