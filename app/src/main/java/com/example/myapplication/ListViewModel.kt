package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel(){








    val allTasks: LiveData<List<Task>>
    private val repository: TaskRepository
    val searchResults: MutableLiveData<List<Task>>

    init {
        val taskDb = TaskRoomDatabase.getInstance(context)
        val taskDao = taskDb.taskDao()
        repository = TaskRepository(taskDao)

        allTasks = repository.allTasks
        searchResults = repository.searchResults
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    fun findTask(name: String) {
        viewModelScope.launch {
            repository.findTask(name)
        }
    }

    fun deleteTask(name: String) {
        viewModelScope.launch {
            repository.deleteTask(name)
        }

    }

    fun clearDatabase() {
        viewModelScope.launch {
            repository.clearDatabase()
        }

    }


}


