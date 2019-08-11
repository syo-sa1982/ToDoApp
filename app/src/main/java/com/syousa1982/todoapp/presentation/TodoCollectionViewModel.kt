package com.syousa1982.todoapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.syousa1982.todoapp.domain.Result
import com.syousa1982.todoapp.domain.usecase.ITodoUseCase
import com.syousa1982.todoapp.util.extention.className
import com.syousa1982.todoapp.util.extention.default
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class TodoCollectionViewModel(private val useCase: ITodoUseCase) : BaseViewModel() {

    /**
     * タスク名
     */
    val taskName = MutableLiveData<String>()


    /**
     * 作成結果
     */
    val createResult = MutableLiveData<Result<Boolean>>()

    /**
     * プログレスバー表示フラグ
     */
    val isProgress = MutableLiveData<Boolean>().default(false)

    /**
     * タスク作成
     */
    fun create() {
        val taskName = taskName.value ?: return
        useCase.addTask(taskName).subscribeBy(
            onNext = { createResult.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }

}