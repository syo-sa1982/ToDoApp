package com.syousa1982.todoapp.di

import android.content.Context
import androidx.room.Room
import com.syousa1982.todoapp.BuildConfig
import com.syousa1982.todoapp.data.db.AppDatabase
import com.syousa1982.todoapp.data.db.dao.TaskDao

object DatabaseModule {
    private const val ROOM_DATABASE_NAME = "Room_Database_${BuildConfig.FLAVOR}"

    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, ROOM_DATABASE_NAME)
            .build()
    }

    fun getTask(db: AppDatabase): TaskDao = db.taskDao()
}