package com.example.astetmanager.ui.screens.schedule

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.astetmanager.R
import com.example.astetmanager.ui.components.MyListItem
import com.example.astetmanager.ui.theme.AstetManagerTheme

@Composable
fun ScheduleScreen(
    navController: NavController,
    viewModel: ScheduleViewModel
) {
    ScheduleScreenContent()
}

@Composable
fun ScheduleScreenContent() {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf(
        stringResource(id = R.string.week),
        stringResource(id = R.string.month),
        stringResource(id = R.string.all)
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                Text(
                    text = stringResource(id = R.string.upcoming_events),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 24.dp, horizontal = 12.dp)
                )
                TabRow(selectedTabIndex = state) {
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
        }
    ) { innerPadding ->
        LazyColumn(Modifier.padding(innerPadding)) {
            repeat(100) {
                item {
                    MyListItem(
                        header = "Hello",
                        body = "World",
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Done, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ScheduleScreenContent_Preview() {
    AstetManagerTheme {
        ScheduleScreenContent()
    }
}
