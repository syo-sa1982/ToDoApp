package com.syousa1982.todoapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.syousa1982.todoapp.domain.Result
import com.syousa1982.todoapp.domain.model.Task
import com.syousa1982.todoapp.domain.usecase.ITodoUseCase
import com.syousa1982.todoapp.util.extention.className
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class TodoViewModel(private val useCase: ITodoUseCase) : BaseViewModel() {

    /**
     * タスクリスト
     */
    val tasks = MutableLiveData<Result<List<Task>>>()

    /**
     * 更新結果
     */
    val updateResult = MutableLiveData<Result<Boolean>>()

    override fun onResume() {
        super.onResume()
        getTasks()
    }

    override fun onDestroy() {
        super.onDestroy()
        tasks.value = null
    }

    fun getTasks() {
        useCase.getTasks().subscribeBy(
            onNext = { tasks.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }

    /**
     * タスクを更新
     */
    fun updateTask(task: Task) {
        val updateTask = Task(
            task.id,
            task.name,
            Task.Status.changeStatus(task.status)
        )
        useCase.updateTask(updateTask).subscribeBy(
            onNext = { updateResult.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }

    /**
     * タスクを削除
     */
    fun delete(task: Task) {
        useCase.removeTask(task).subscribeBy(
            onNext = { updateResult.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }
}