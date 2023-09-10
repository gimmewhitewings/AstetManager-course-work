package com.example.astetmanager.ui.screens.complect.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.astetmanager.R

@Composable
fun ComplectPartsAmountsBlock(
    modifier: Modifier = Modifier,
    pillowcasesAmount: Int,
    onRemovePillowcaseButtonClick: () -> Unit,
    onAddPillowcaseButtonClick: () -> Unit,
    sheetsAmount: Int,
    onRemoveSheetButtonClick: () -> Unit,
    onAddSheetButtonClick: () -> Unit,
    duvetCoversAmount: Int,
    onRemoveDuvetCoverButtonClick: () -> Unit,
    onAddDuvetCoverButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        // Pillowcases
        ComplectPartAmountChooser(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.pillowcases),
            counterValue = pillowcasesAmount,
            onRemoveButtonClick = onRemovePillowcaseButtonClick,
            onAddButtonClick = onAddPillowcaseButtonClick
        )
        // Sheets
        ComplectPartAmountChooser(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.sheets),
            counterValue = sheetsAmount,
            onRemoveButtonClick = onRemoveSheetButtonClick,
            onAddButtonClick = onAddSheetButtonClick
        )
        // Duvet covers
        ComplectPartAmountChooser(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.duvet_covers),
            counterValue = duvetCoversAmount,
            onRemoveButtonClick = onRemoveDuvetCoverButtonClick,
            onAddButtonClick = onAddDuvetCoverButtonClick
        )
    }
}
