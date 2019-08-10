package com.syousa1982.todoapp.data.repository

import com.syousa1982.todoapp.data.db.dao.TaskDao
import com.syousa1982.todoapp.data.db.entity.TaskEntity
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
     * @param tasks
     */
    fun updateTasksFromDB(tasks: List<TaskEntity>): Single<Int>
    /**
     * タスク削除
     *
     * @param tasks
     */
    fun deleteTaskFromDB(tasks: TaskEntity): Single<Int>

    /**
     * タスクを複数削除
     *
     * @param tasks
     */
    fun deleteTasksFromDB(tasks: List<TaskEntity>): Single<Int>


    /**
     * タスクを無条件に取得
     */
    fun loadTasksFromDB(): Single<List<TaskEntity>>


    /**
     * タスクを無条件に取得
     *
     * @param status
     */
    fun loadTasksByStatusFromDB(status: String): Single<List<TaskEntity>>
}

class TaskRepository(private val dao: TaskDao) : ITaskRepository {
    override fun insertTaskFromDB(task: TaskEntity): Single<Long> {
        return dao.insertTask(task)
    }

    override fun updateTaskFromDB(task: TaskEntity): Single<Int> {
        return dao.updateTask(task)
    }

    override fun updateTasksFromDB(tasks: List<TaskEntity>): Single<Int> {
        return dao.updateTasks(tasks)
    }

    override fun deleteTaskFromDB(tasks: TaskEntity): Single<Int> {
        return dao.deleteTask(tasks)
    }

    override fun deleteTasksFromDB(tasks: List<TaskEntity>): Single<Int> {
        return dao.deleteTasks(tasks)
    }

    override fun loadTasksFromDB(): Single<List<TaskEntity>> {
        return dao.loadTasks()
    }

    override fun loadTasksByStatusFromDB(status: String): Single<List<TaskEntity>> {
        return dao.loadTasksByStatus(status)
    }
}