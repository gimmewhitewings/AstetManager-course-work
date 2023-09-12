package com.example.astetmanager.ui.screens.cloth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.astetmanager.R
import com.example.astetmanager.data.ClothEntity
import com.example.astetmanager.ui.screens.cloth.components.ClothListItem
import com.example.astetmanager.ui.theme.AstetManagerTheme

@Composable
fun ClothScreen(
    navController: NavController,
    viewModel: ClothViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    ClothScreenContent(
        onNavigationButtonClick = { navController.popBackStack() },
        clothName = viewState.clothName,
        setClothName = viewModel::setClothName,
        onClearClothNameButtonClick = viewModel::clearClothName,
        onSaveClothButtonClick = viewModel::saveCloth,
        newPartInMetersText = viewState.newPartInMetersText,
        addNewClothPart = viewModel::addNewClothPart,
        setNewPartInMetersText = viewModel::setNewPartInMetersText,
        clothEntities = viewState.clothEntities
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothScreenContent(
    onNavigationButtonClick: () -> Unit,
    clothName: String,
    setClothName: (String) -> Unit,
    onClearClothNameButtonClick: () -> Unit,
    onSaveClothButtonClick: () -> Unit,
    newPartInMetersText: String,
    setNewPartInMetersText: (String) -> Unit,
    addNewClothPart: () -> Unit,
    clothEntities: List<ClothEntity>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.cloth_record)) },
                navigationIcon = {
                    IconButton(onClick = onNavigationButtonClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    TextButton(onClick = onSaveClothButtonClick) {
                        Text(text = stringResource(id = R.string.save))
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .size(144.dp)
                    .clip(shape = RoundedCornerShape(20))
                    .background(color = Color.Gray)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = clothName,
                onValueChange = setClothName,
                label = { Text(text = stringResource(id = R.string.cloth)) },
                trailingIcon = {
                    if (clothName.isNotEmpty()) {
                        IconButton(onClick = onClearClothNameButtonClick) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                        }
                    }
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = newPartInMetersText,
                onValueChange = setNewPartInMetersText,
                label = { Text(text = stringResource(id = R.string.new_cloth_parth)) },
                trailingIcon = {
                    if (newPartInMetersText.isNotEmpty()) {
                        IconButton(onClick = addNewClothPart) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                )
            )

            LazyColumn(
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                clothEntities.forEach {
                    item {
                        ClothListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            onDeleteButtonClick = {},
                            lengthInMeters = it.lengthInMeters
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ClothScreenContent_Preview() {
    AstetManagerTheme {
        ClothScreenContent(
            onNavigationButtonClick = {},
            clothName = "",
            setClothName = {},
            onClearClothNameButtonClick = {},
            onSaveClothButtonClick = {},
            newPartInMetersText = "",
            setNewPartInMetersText = {},
            addNewClothPart = {},
            clothEntities = listOf(
                ClothEntity(
                    id = 0,
                    name = "Шерсть",
                    lengthInMeters = 5
                ),
                ClothEntity(
                    id = 1,
                    name = "Хлопок",
                    lengthInMeters = 10
                ),
                ClothEntity(
                    id = 2,
                    name = "Лён",
                    lengthInMeters = 15
                ),
                ClothEntity(
                    id = 3,
                    name = "Шёлк",
                    lengthInMeters = 20
                )
            )
        )
    }
}
