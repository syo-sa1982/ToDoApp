package com.syousa1982.todoapp.util.extention

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

fun RecyclerView.getGroupieAdapter(): GroupAdapter<*> {
    val adapter = adapter
    if (adapter is GroupAdapter) {
        return adapter
    }
    throw IllegalAccessException("${GroupAdapter::class.java.simpleName}が設定されていません")
}

fun RecyclerView.setGroupieAdapter() {
    adapter = GroupAdapter<ViewHolder>()
}

fun RecyclerView.setLinearLayoutManagerWithDivider() {
    val linearLayoutManager = LinearLayoutManager(context)
    layoutManager = linearLayoutManager
    val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
    addItemDecoration(dividerItemDecoration)
}

inline fun <reified T : Group> RecyclerView.setGroupieOnItemClickListener(crossinline onItemClickListener: (T, View) -> Unit) {
    val adapter = adapter
    if (adapter is GroupAdapter) {
        adapter.setOnItemClickListener { item, view ->
            view.pauseClickTimer()
            if (item is T) onItemClickListener(item, view)
        }
    }
}

inline fun <reified T : Group> RecyclerView.setGroupieOnItemLongClickListener(crossinline onItemLongClickListener: (T, View) -> Boolean) {
    val adapter = adapter
    if (adapter is GroupAdapter) {
        adapter.setOnItemLongClickListener { item, view ->
            view.pauseClickTimer()
            return@setOnItemLongClickListener if (item is T) onItemLongClickListener(item, view) else false
        }
    }
}