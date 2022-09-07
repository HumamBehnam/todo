package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Query("SELECT * FROM tasks WHERE taskTitle = :title")
    fun findTask(title: String): List<Task>

    @Query("DELETE FROM tasks WHERE taskTitle = :title")
    fun deleteTask(title: String)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks ORDER BY taskStatus")
    fun getAllTasksOrdered(): LiveData<List<Task>>

    @Query("DELETE FROM tasks")
    fun clearDatabase(): Unit
}