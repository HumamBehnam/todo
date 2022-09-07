package com.example.myapplication

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class AppState @Inject constructor() {
    var currentTask: Task = Task()


    fun setEmptyState() {
        currentTask = Task()
    }

    fun newTask(newTask: Task){
        currentTask = newTask
    }
}