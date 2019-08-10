package com.syousa1982.todoapp.data.repository

import com.syousa1982.todoapp.data.db.dao.TaskDao
import com.syousa1982.todoapp.data.db.entity.TaskEntity
import com.syousa1982.todoapp.domain.model.Task
import io.reactivex.Single


interface ITaskRepository {
    /**
     * タスクを追加
     *
     * @param task
     */
    fun insertTaskFromDB(task: TaskEntity): Single<Long>

    /**
     * タスクを更新
     *
     * @param task
     */
    fun updateTaskFromDB(task: TaskEntity): Single<Int>

    /**
     * タスクを更新
     *
     * @param status
     */
    fun updateTasksByStatusFromDB(status: Task.Status): Single<Int>
    /**
     * タスク削除
     *
     * @param tasks
     */
    fun deleteTaskFromDB(tasks: TaskEntity): Single<Int>

    /**
     * 指定されたステータスのタスクを複数削除
     *
     * @param status
     */
    fun deleteTasksByStatusFromDB(status: Task.Status): Single<Int>


    /**
     * タスクを取得
     */
    fun loadTasksFromDB(): Single<List<TaskEntity>>


    /**
     * 指定されたステータスのタスクを取得
     *
     * @param status
     */
    fun loadTasksByStatusFromDB(status: Task.Status): Single<List<TaskEntity>>
}

class TaskRepository(private val dao: TaskDao) : ITaskRepository {
    override fun insertTaskFromDB(task: TaskEntity): Single<Long> {
        return dao.insertTask(task)
    }

    override fun updateTaskFromDB(task: TaskEntity): Single<Int> {
        return dao.updateTask(task)
    }

    override fun updateTasksByStatusFromDB(status: Task.Status): Single<Int> {
        return dao.updateAllTasksStatus(status.value)
    }

    override fun deleteTaskFromDB(tasks: TaskEntity): Single<Int> {
        return dao.deleteTask(tasks)
    }

    override fun deleteTasksByStatusFromDB(status: Task.Status): Single<Int> {
        return dao.deleteTasksByStatus(status.value)
    }

    override fun loadTasksFromDB(): Single<List<TaskEntity>> {
        return dao.loadTasks()
    }

    override fun loadTasksByStatusFromDB(status: Task.Status): Single<List<TaskEntity>> {
        return dao.loadTasksByStatus(status.value)
    }
}