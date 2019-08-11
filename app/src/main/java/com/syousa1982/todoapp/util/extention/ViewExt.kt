package com.syousa1982.todoapp.util.extention

import android.os.Handler
import android.view.View

/**
 * VisibleとGoneを切り替える
 *
 * @param isVisible Visibleであるかどうか
 */
fun View.visibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

/**
 * VisibleとInvisibleを切り替える
 *
 * @param isVisible Visibleであるかどうか
 */
fun View.visibleOrInvisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

/**
 * GoneとVisibleを切り替える
 *
 * @param isGone Goneであるかどうか
 */
fun View.goneOrVisible(isGone: Boolean) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}

/**
 * InvisibleとVisibleを切り替える
 *
 * @param isInvisible Invisibleであるかどうか
 */
fun View.invisibleOrVisible(isInvisible: Boolean) {
    visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE
}

/**
 * 連打対策のためViewを一定時間クリック無効にするクリックリスナー
 *
 * @param onClickListener Unit
 */
fun View.setOnClickPauseListener(onClickListener: (View) -> Unit) {
    if (hasOnClickListeners()) {
        return
    }
    setOnClickListener {
        pauseClickTimer()
        onClickListener.invoke(it)
    }
}

/**
 * 連打対策のためViewを一定時間クリック無効にする
 *
 * @param delayMillis クリックの無効化時間（ミリ秒）
 */
fun View.pauseClickTimer(delayMillis: Long = 500) {
    isClickable = false
    Handler().postDelayed({ isClickable = true }, delayMillis)
}