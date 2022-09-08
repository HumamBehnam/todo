package com.example.myapplication

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditViewModel @Inject constructor(
    @ApplicationContext context: Context,
    var appState: AppState,
) : ViewModel(){

    var currTask = mutableStateOf(appState.currentTask)


    private val repository: TaskRepository

    init {
        val taskDb = TaskRoomDatabase.getInstance(context)
        val taskDao = taskDb.taskDao()
        repository = TaskRepository(taskDao)
    }


    fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    fun updateName(newTitle: String) {
        val temp = appState.currentTask
        temp.title = newTitle
        currTask.value = temp
        appState.currentTask = temp

        println("")

    }

    fun updateDescription(newDescription: String) {
        val temp = appState.currentTask
        temp.description = newDescription

        currTask.value = temp
        appState.currentTask = temp

        println("")

    }

    fun resetState() {
        appState.setEmptyState()
        currTask = mutableStateOf(appState.currentTask)
    }


}