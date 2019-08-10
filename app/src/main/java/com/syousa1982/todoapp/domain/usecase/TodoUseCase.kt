package com.syousa1982.todoapp.domain.usecase

import com.syousa1982.todoapp.data.db.entity.TaskEntity
import com.syousa1982.todoapp.data.repository.ITaskRepository
import com.syousa1982.todoapp.domain.Result
import com.syousa1982.todoapp.domain.model.Task
import com.syousa1982.todoapp.domain.translator.TaskTranslator
import com.syousa1982.todoapp.util.extention.toResult
import com.syousa1982.todoapp.util.rx.SchedulerProvider
import io.reactivex.Flowable


/**
 * ToDo機能 インタフェース
 */
interface ITodoUseCase {

    /**
     * タスクを取得
     */
    fun getTasks(): Flowable<Result<List<Task>>>

    /**
     * 未完了のタスクを取得
     */
    fun getActiveTasks(): Flowable<Result<List<Task>>>

    /**
     * 完了状態のタスクを取得
     */
    fun getCompletedTasks(): Flowable<Result<List<Task>>>

    fun addTask(name: String): Flowable<Result<Boolean>>

    fun updateTask(task: Task): Flowable<Result<Boolean>>

    fun removeTask(task: Task): Flowable<Result<Boolean>>

    fun removeCompletedTasks(): Flowable<Result<Boolean>>
}

class TodoUseCase(
    private val repository: ITaskRepository,
    private val schedulerProvider: SchedulerProvider
) : ITodoUseCase {

    override fun getTasks(): Flowable<Result<List<Task>>> {
        return repository.loadTasksFromDB()
            .map { TaskTranslator.toModels(it) }
            .toResult(schedulerProvider)
    }

    override fun getActiveTasks(): Flowable<Result<List<Task>>> {
        return repository.loadTasksByStatusFromDB(Task.Status.ACTIVE)
            .map { TaskTranslator.toModels(it) }
            .toResult(schedulerProvider)
    }

    override fun getCompletedTasks(): Flowable<Result<List<Task>>> {
        return repository.loadTasksByStatusFromDB(Task.Status.COMPLETE)
            .map { TaskTranslator.toModels(it) }
            .toResult(schedulerProvider)
    }

    override fun addTask(name: String): Flowable<Result<Boolean>> {
        val entity = TaskEntity(name = name, status = Task.Status.ACTIVE.value)
        return repository.insertTaskFromDB(entity)
            .map { true }
            .toResult(schedulerProvider)
    }

    override fun updateTask(task: Task): Flowable<Result<Boolean>> {
        val entity = TaskEntity(task.id, task.name, task.status.value)
        return repository.updateTaskFromDB(entity)
            .map { true }
            .toResult(schedulerProvider)
    }

    override fun removeTask(task: Task): Flowable<Result<Boolean>> {
        val entity = TaskEntity(task.id, task.name, task.status.value)
        return repository.deleteTaskFromDB(entity)
            .map { true }
            .toResult(schedulerProvider)
    }

    override fun removeCompletedTasks(): Flowable<Result<Boolean>> {
        return repository.deleteTasksByStatusFromDB(Task.Status.COMPLETE)
            .map { true }
            .toResult(schedulerProvider)
    }
}