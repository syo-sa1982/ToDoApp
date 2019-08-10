package com.syousa1982.todoapp.data.db.dao

import androidx.room.*
import com.syousa1982.todoapp.data.db.entity.TaskEntity
import io.reactivex.Single

@Dao
interface TaskDao {

    /**
     * タスクを追加
     *
     * @param task
     */
    @Insert
    fun insertTask(task: TaskEntity): Single<Long>

    /**
     * タスクを更新
     *
     * @param task
     */
    @Update
    fun updateTask(task: TaskEntity): Single<Int>
    /**
     * タスクを複数更新
     *
     * @param tasks
     */
    @Update
    fun updateTasks(tasks: List<TaskEntity>): Single<Int>

    /**
     * タスク削除
     *
     * @param tasks
     */
    @Delete
    fun deleteTask(tasks: TaskEntity): Single<Int>

    /**
     * タスクを複数削除
     *
     * @param tasks
     */
    @Delete
    fun deleteTasks(tasks: List<TaskEntity>): Single<Int>

    /**
     * タスクを無条件に取得
     */
    @Transaction
    @Query("SELECT * FROM tasks")
    fun loadTasks(): Single<List<TaskEntity>>

    /**
     * 指定されたステータスでタスクを取得
     *
     * @param status
     */
    @Transaction
    @Query("SELECT * FROM tasks WHERE status = :status")
    fun loadTasksByStatus(status: String): Single<List<TaskEntity>>

}