package com.syousa1982.todoapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.syousa1982.todoapp.constant.TodoCollectionKind
import com.syousa1982.todoapp.domain.Result
import com.syousa1982.todoapp.domain.model.Task
import com.syousa1982.todoapp.domain.usecase.ITodoUseCase
import com.syousa1982.todoapp.util.extention.className
import com.syousa1982.todoapp.util.extention.default
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class TodoActiveViewModel(private val useCase: ITodoUseCase) : BaseViewModel() {
    /**
     * タスクリスト
     */
    val tasks = MutableLiveData<Result<List<Task>>>()

    /**
     * 表示中のタブ名
     */
    val kind = MutableLiveData<TodoCollectionKind>()

    /**
     * 更新結果
     */
    val updateResult = MutableLiveData<Result<Boolean>>()

    /**
     * プログレスバー表示フラグ
     */
    val isProgress = MutableLiveData<Boolean>().default(false)


    override fun onCreate() {
        super.onCreate()
        Log.d(className(), "onCreate # kind of ${kind.value}")
    }

    override fun onStart() {
        super.onStart()
        Log.d(className(), "onStart # kind of ${kind.value}")
    }

    override fun onResume() {
        super.onResume()
        Log.d(className(), "onResume # kind of ${kind.value}")
        getTasks()
    }

    override fun onStop() {
        super.onStop()
        Log.d(className(), "onStop # kind of ${kind.value}")
    }

    override fun onPause() {
        super.onPause()
        Log.d(className(), "onPause # kind of ${kind.value}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(className(), "onCleared # kind of ${kind.value}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(className(), "onDestroy # kind of ${kind.value}")
        tasks.value = null
    }


    fun getTasks() {
        useCase.getActiveTasks().subscribeBy(
            onNext = { tasks.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }

    fun getTasksByKind(kind: TodoCollectionKind) {
        Log.e(className(), "kind of ${kind.value}")
        when (kind) {
            TodoCollectionKind.ALL -> useCase.getTasks()
            TodoCollectionKind.ACTIVE -> useCase.getActiveTasks()
            TodoCollectionKind.COMPLETED -> useCase.getCompletedTasks()
        }.subscribeBy(
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