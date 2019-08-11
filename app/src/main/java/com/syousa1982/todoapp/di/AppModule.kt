package com.syousa1982.todoapp.di

import com.syousa1982.todoapp.util.rx.AppSchedulerProvider
import com.syousa1982.todoapp.util.rx.SchedulerProvider
import com.syousa1982.todoapp.data.repository.ITaskRepository
import com.syousa1982.todoapp.data.repository.TaskRepository
import com.syousa1982.todoapp.domain.usecase.ITodoUseCase
import com.syousa1982.todoapp.domain.usecase.TodoUseCase
import com.syousa1982.todoapp.presentation.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val instance = module {

        viewModel { TodoViewModel(get()) }
        viewModel { TodoActiveViewModel(get()) }
        viewModel { TodoCompletedViewModel(get()) }
        viewModel { TodoCollectionViewModel(get()) }

        // region Domain Layer
        factory<ITodoUseCase> { TodoUseCase(get(), get()) }
        // endregion

        // region Data Layer Database
        single { DatabaseModule.getDatabase(androidContext()) }
        single { DatabaseModule.getTask(get()) }
        // endregion

        // region Data Layer Repository
        single<ITaskRepository> { TaskRepository(get()) }
        // endregion

        // region Rx
        single<SchedulerProvider> { AppSchedulerProvider() }
        // endregion
    }
}