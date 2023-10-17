package com.example.astetmanager.ui.screens.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.astetmanager.R
import com.example.astetmanager.data.database.entities.PartType
import com.example.astetmanager.data.database.entities.PartTypeWithTasks
import com.example.astetmanager.data.database.entities.Task
import com.example.astetmanager.data.database.entities.enums.getStringResourceId

@Composable
fun ScheduleScreen(
    navController: NavController,
    viewModel: ScheduleViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    ScheduleScreenContent(
        partTypeWithTasks = viewState.partTypeWithTasks,
        onToggleTaskCompletion = viewModel::toggleTaskCompletion
    )
}

@Composable
fun ScheduleScreenContent(
    partTypeWithTasks: List<PartTypeWithTasks>,
    onToggleTaskCompletion: (Int, Boolean) -> Unit
) {
    var topAppBarSelectedTabIndex by remember { mutableIntStateOf(0) }
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
                TabRow(selectedTabIndex = topAppBarSelectedTabIndex) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = topAppBarSelectedTabIndex == index,
                            onClick = { topAppBarSelectedTabIndex = index },
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
        LazyColumn(
            Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//            repeat(100) {
//                item {
//                    MyListItem(
//                        header = "Hello",
//                        body = "World",
//                        modifier = Modifier.padding(12.dp)
//                    ) {
//                        Icon(imageVector = Icons.Default.Done, contentDescription = null)
//                    }
//                }
//            }
            partTypeWithTasks.forEach {
                val partType = it.partType
                val tasks = it.tasks
                items(tasks) { task ->
                    TaskItem(
                        modifier = Modifier.fillMaxWidth(),
                        isCompleted = task.isCompleted,
                        onCheckBoxChecked = onToggleTaskCompletion,
                        taskId = task.taskId!!,
                        partType = partType
                    )
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    partType: PartType,
    modifier: Modifier = Modifier,
    isCompleted: Boolean,
    taskId: Int,
    onCheckBoxChecked: (Int, Boolean) -> Unit,
) {
    Card(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "${stringResource(partType.partTypeClass.getStringResourceId())} ${partType.partTypeSize} ${partType.articular}",
                style = typography.headlineSmall
            )

            Checkbox(
                checked = isCompleted,
                onCheckedChange = {
                    onCheckBoxChecked(taskId, it)
                }
            )
        }
    }
}

//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
//@Composable
//fun ScheduleScreenContent_Preview() {
//    AstetManagerTheme {
//        ScheduleScreenContent()
//    }
//}
