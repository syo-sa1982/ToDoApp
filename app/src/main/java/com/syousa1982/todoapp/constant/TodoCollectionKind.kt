package com.syousa1982.todoapp.constant


enum class TodoCollectionKind(val value: Int) {
    ALL(0),
    ACTIVE(1),
    COMPLETED(2);

    fun getTitle(): String = when (this) {
        ALL -> "All"
        ACTIVE -> "Active"
        COMPLETED -> "Completed"
    }

    companion object {
        fun from(value: Int) = values().first { it.value == value }
    }
}