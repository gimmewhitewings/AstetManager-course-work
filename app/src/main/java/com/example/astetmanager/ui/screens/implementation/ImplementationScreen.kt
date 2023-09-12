package com.example.astetmanager.ui.screens.implementation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun ImplementationScreen(
    navController: NavController,
    viewModel: ImplementationViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    ImplementationScreenContent()
}

@Composable
fun ImplementationScreenContent() {
    TODO("Not yet implemented")
}
