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
     * @param status
     */
    @Transaction
    @Query("UPDATE tasks set status = :status")
    fun updateAllTasksStatus(status: String): Single<Int>

    /**
     * タスク削除
     *
     * @param tasks
     */
    @Delete
    fun deleteTask(tasks: TaskEntity): Single<Int>

    /**
     * 指定されたステータスのタスクを複数削除
     *
     * @param status
     */
    @Transaction
    @Query("DELETE FROM tasks WHERE status = :status")
    fun deleteTasksByStatus(status: String): Single<Int>

    /**
     * タスクを無条件に取得
     */
    @Query("SELECT * FROM tasks")
    fun loadTasks(): Single<List<TaskEntity>>

    /**
     * 指定されたステータスでタスクを取得
     *
     * @param status
     */
    @Query("SELECT * FROM tasks WHERE status = :status")
    fun loadTasksByStatus(status: String): Single<List<TaskEntity>>

}