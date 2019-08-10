package com.syousa1982.todoapp.util.extention

import androidx.lifecycle.*

// region 監視(observer)

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) = observe(owner, Observer {
    if (it != null) observer.invoke(it)
})

// endregion

// region 変換(map)

fun <X, Y> LiveData<X>.map(func: (X) -> Y) = Transformations.map(this, func)!!

fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>) = Transformations.switchMap(this, func)!!

// endregion

// region 初期(default)

fun <T, U : MutableLiveData<T>> U.default(value: T) = apply { setValue(value) }

// endregion

// region 取得(is/get)

fun LiveData<String>?.isNotEmpty(): Boolean = this?.value?.isNotEmpty() ?: false

fun LiveData<String>?.isEmpty(): Boolean = this?.value?.isEmpty() ?: false

fun LiveData<Boolean>?.isTrue(): Boolean = this?.value ?: false

fun LiveData<Boolean>?.isFalse(): Boolean = this?.value?.not() ?: false

fun <T> LiveData<T>?.isNotNull(): Boolean = this?.value != null

// endregion