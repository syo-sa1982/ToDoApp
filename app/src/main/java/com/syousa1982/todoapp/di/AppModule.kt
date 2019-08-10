package com.syousa1982.todoapp.di

import com.syousa1982.todoapp.data.repository.ITaskRepository
import com.syousa1982.todoapp.data.repository.TaskRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object AppModule {
    val instance = module {

        // region Data Layer Database
        single { DatabaseModule.getDatabase(androidContext()) }
        single { DatabaseModule.getTask(get()) }
        // endregion

        // region Data Layer Repository
        single<ITaskRepository> { TaskRepository(get()) }
        // endregion
    }
}