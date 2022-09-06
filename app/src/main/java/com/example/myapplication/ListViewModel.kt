package com.example.myapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() : ViewModel(){



    var taskList : MutableList<Task> = mutableStateListOf(
        Task("hej", "vaaaa", true),
        Task("då", "vaaaa", false),
        Task("blabla", "vaaaa", true),

        ).ordered(statusNow = true)


    fun MutableList<Task>.ordered(
        statusNow: Boolean
    ): MutableList<Task> =
        if (statusNow) this.sortedBy { !it.status }.toMutableList()
        else this.sortedByDescending { !it.status }.toMutableList()

    fun deleteTask(index: Int){
        println(taskList.toList())
        taskList.removeAt(index)
    }


}


data class Task(
    val title: String,
    val description: String,
    val status: Boolean
){

    operator fun compareTo(t: Task): Int {
        return this.status.compareTo(t.status)
    }
}