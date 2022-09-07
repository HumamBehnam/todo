package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()
    val searchResults = MutableLiveData<List<Task>>()

    suspend fun insertTask(newTask: Task) {
        withContext(Dispatchers.IO) {
            taskDao.insertTask(newTask)
        }
    }

    suspend fun deleteTask(name: String) {
        withContext(Dispatchers.IO){
            taskDao.deleteTask(name)
        }
    }

    suspend fun findTask(name: String) {
        withContext(Dispatchers.IO) {
            searchResults.value = taskDao.findTask(name)
        }
    }

    suspend fun clearDatabase() {
        withContext(Dispatchers.IO) {
            taskDao.clearDatabase()
        }
    }


}