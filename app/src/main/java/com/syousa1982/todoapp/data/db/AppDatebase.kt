package com.syousa1982.todoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syousa1982.todoapp.data.db.dao.TaskDao
import com.syousa1982.todoapp.data.db.entity.TaskEntity

@Database(entities = arrayOf(TaskEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}