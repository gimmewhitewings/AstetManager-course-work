package com.example.astetmanager.ui.screens.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astetmanager.data.AppRepository
import com.example.astetmanager.data.database.entities.PartTypeWithTasks
import com.example.astetmanager.data.database.entities.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    fun toggleTaskCompletion(taskId: Int, completion: Boolean) {
        viewModelScope.launch {
            repository.toggleTaskCompletion(taskId, completion)
        }
    }

    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getPartTypesWithTasks().collect { partTypeWithTasks ->
                _uiState.update {
                    it.copy(
                        partTypeWithTasks = partTypeWithTasks
                    )
                }
            }
        }
    }
}

data class ScheduleUiState(
    val partTypeWithTasks: List<PartTypeWithTasks> = emptyList()
)
