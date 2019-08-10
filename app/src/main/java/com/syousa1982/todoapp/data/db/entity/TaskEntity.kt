package com.syousa1982.todoapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * TaskEntity Entity
 */
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskListId: Int,
    val name: String,
    val status: String
)