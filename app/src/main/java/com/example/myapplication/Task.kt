package com.example.myapplication

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "tasks")
class Task{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "taskId")
    var id: Int = 0

    @ColumnInfo(name = "taskTitle")
    var title: String = ""

    @ColumnInfo(name = "taskDescription")
    var description: String = ""

    @ColumnInfo(name = "taskStatus")
    var status: Boolean = false

    constructor() {}

    constructor(title: String, description: String, status: Boolean) {
        this.id = id
        this.title = title
        this.description = description
        this.status = status
    }
    constructor(id: Int, title: String, description: String, status: Boolean) {
        this.id = id
        this.title = title
        this.description = description
        this.status = status
    }

    constructor(title: String, status: Boolean) {
        this.id = id
        this.title = title
        this.status = status
    }



    operator fun compareTo(t: Task): Int {
        return this.status.compareTo(t.status)
    }


}