package com.example.astetmanager.ui.screens.new_order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.astetmanager.R
import com.example.astetmanager.data.database.entities.enums.Counterparty
import com.example.astetmanager.data.database.entities.enums.PartTypeSize
import com.example.astetmanager.data.database.entities.enums.PaymentMethod
import com.example.astetmanager.data.database.entities.enums.getStringResourceId
import com.example.astetmanager.ui.screens.complect.components.ComplectPartsAmountsBlock
import com.example.astetmanager.ui.screens.complect.components.ComplectSizeChooser
import com.example.astetmanager.ui.theme.AstetManagerTheme

@Composable
fun NewOrderScreen(
    navController: NavController,
    viewModel: NewOrderViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    NewOrderScreenContent(
        onNavigationIconClick = { navController.popBackStack() },
        counterparty = viewState.counterparty,
        setCounterparty = viewModel::setCounterparty,
        paymentMethod = viewState.paymentMethod,
        setPaymentMethod = viewModel::setPaymentMethod,
        designText = viewState.articularText,
        setDesignText = viewModel::setDesignText,
        selectedComplectSize = viewState.selectedComplectSize,
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
//        onClothButtonClick = {},
//        onChooseDateButtonClick = {},
//        onDeliveryButtonClick = {},
        priceText = if (viewState.price == 0) "" else viewState.price.toString(),
        setPriceText = viewModel::setPrice
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewOrderScreenContent(
    onNavigationIconClick: () -> Unit,
    counterparty: Counterparty,
    setCounterparty: (Counterparty) -> Unit,
    paymentMethod: PaymentMethod,
    setPaymentMethod: (PaymentMethod) -> Unit,
    designText: String,
    setDesignText: (String) -> Unit,
    selectedComplectSize: PartTypeSize,
    setComplectSize: (PartTypeSize) -> Unit,
    pillowcasesAmount: Int,
    onAddPillowcaseButtonClick: () -> Unit,
    onRemovePillowcaseButtonClick: () -> Unit,
    sheetsAmount: Int,
    onAddSheetButtonClick: () -> Unit,
    onRemoveSheetButtonClick: () -> Unit,
    duvetCoversAmount: Int,
    onAddDuvetCoverButtonClick: () -> Unit,
    onRemoveDuvetCoverButtonClick: () -> Unit,
//    onClothButtonClick: () -> Unit,
//    onChooseDateButtonClick: () -> Unit,
//    onDeliveryButtonClick: () -> Unit,
    priceText: String,
    setPriceText: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.new_order)) },
                navigationIcon = {
                    IconButton(onClick = onNavigationIconClick) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var counterPartyDropDownMenuState by remember { mutableStateOf(false) }
            var paymentMethodDropDownMenuState by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = counterPartyDropDownMenuState,
                    onExpandedChange = { counterPartyDropDownMenuState = it },
                    modifier = Modifier.weight(3f)
                ) {
                    OutlinedTextField(
                        value = stringResource(id = counterparty.getStringResourceId()),
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = counterPartyDropDownMenuState
                            )
                        },
                        modifier = Modifier.menuAnchor(),
                        label = { Text(text = stringResource(id = R.string.counterparty)) }
                    )

                    ExposedDropdownMenu(
                        expanded = counterPartyDropDownMenuState,
                        onDismissRequest = { counterPartyDropDownMenuState = false }
                    ) {
                        Counterparty.entries.forEach {
                            DropdownMenuItem(
                                onClick = { setCounterparty(it) },
                                text = { Text(text = stringResource(id = it.getStringResourceId())) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                ExposedDropdownMenuBox(
                    expanded = paymentMethodDropDownMenuState,
                    onExpandedChange = { paymentMethodDropDownMenuState = it },
                    modifier = Modifier.weight(2f)
                ) {
                    OutlinedTextField(
                        value = stringResource(id = paymentMethod.getStringResourceId()),
                        maxLines = 1,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = paymentMethodDropDownMenuState
                            )
                        },
                        modifier = Modifier.menuAnchor(),
                        label = { Text(text = stringResource(id = R.string.payment_method)) }
                    )

                    ExposedDropdownMenu(
                        expanded = paymentMethodDropDownMenuState,
                        onDismissRequest = { paymentMethodDropDownMenuState = false }
                    ) {
                        PaymentMethod.entries.forEach {
                            DropdownMenuItem(
                                onClick = { setPaymentMethod(it) },
                                text = { Text(text = stringResource(id = it.getStringResourceId())) }
                            )
                        }
                    }
                }
            }
            OutlinedTextField(
                value = designText,
                onValueChange = setDesignText,
                label = { Text(text = stringResource(id = R.string.design)) },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            ComplectSizeChooser(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                selectedComplectSize = selectedComplectSize,
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

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//            ) {
//                ElevatedButton(
//                    onClick = onClothButtonClick,
//                    modifier = Modifier.weight(1f)
//                ) {
//                    Text(text = stringResource(id = R.string.cloth))
//                }
//
//                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
//
//                ElevatedButton(
//                    onClick = onChooseDateButtonClick,
//                    modifier = Modifier.weight(2f)
//                ) {
//                    Text(text = stringResource(id = R.string.choose_date))
//                }
//            }
//
//            ElevatedButton(
//                onClick = onDeliveryButtonClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//            ) {
//                Text(text = stringResource(id = R.string.delivery))
//            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = priceText,
                onValueChange = setPriceText,
                label = { Text(text = stringResource(id = R.string.price)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_ruble),
                        contentDescription = null
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun ApplicationScreenContent_Preview() {
    AstetManagerTheme {
        NewOrderScreenContent(
            onNavigationIconClick = { },
            counterparty = Counterparty.OZON,
            setCounterparty = {},
            paymentMethod = PaymentMethod.CASH,
            setPaymentMethod = {},
            designText = "hey",
            setDesignText = {},
            selectedComplectSize = PartTypeSize.M,
            setComplectSize = {},
            pillowcasesAmount = 2,
            onAddPillowcaseButtonClick = { },
            onRemovePillowcaseButtonClick = { },
            sheetsAmount = 1,
            onAddSheetButtonClick = { },
            onRemoveSheetButtonClick = { },
            duvetCoversAmount = 1,
            onAddDuvetCoverButtonClick = { },
            onRemoveDuvetCoverButtonClick = { },
//            onClothButtonClick = { },
//            onChooseDateButtonClick = { },
//            onDeliveryButtonClick = { },
            priceText = "1000",
            setPriceText = {}
        )
    }
}
