package com.syousa1982.todoapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.syousa1982.todoapp.domain.Result
import com.syousa1982.todoapp.domain.model.Task
import com.syousa1982.todoapp.domain.usecase.ITodoUseCase
import com.syousa1982.todoapp.util.extention.className
import com.syousa1982.todoapp.util.extention.default
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class TodoViewModel(private val useCase: ITodoUseCase) : BaseViewModel() {

    /**
     * タスクリスト
     */
    val tasks = MutableLiveData<Result<List<Task>>>()

    /**
     * プログレスバー表示フラグ
     */
    val isProgress = MutableLiveData<Boolean>().default(false)


    override fun onResume() {
        super.onResume()
        useCase.getTasks().subscribeBy(
            onNext = { tasks.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }
}